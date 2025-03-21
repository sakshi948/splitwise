package com.example.splitwise.service;

import com.example.splitwise.helper.Pair;
import com.example.splitwise.helper.Transaction;
import com.example.splitwise.models.ExpenseTracker;
import com.example.splitwise.repository.ExpenseTrackerRepositroy;
import com.example.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseTrackerService {
    private final ExpenseTrackerRepositroy expenseTrackerRepository;
    private final UserRepository userRepository;
    ExpenseTrackerService(ExpenseTrackerRepositroy expenseTrackerRepository, UserRepository userRepository) {
        this.expenseTrackerRepository = expenseTrackerRepository;
        this.userRepository = userRepository;
    }

    public void addExpense(Integer groupId, Double amount, Integer paidBy, List<Long> members, String description) {
    double expensePerPerson = amount/members.size();
    for (Long member : members) {
            ExpenseTracker expense = new ExpenseTracker();
            expense.setAmount(expensePerPerson);
            expense.setPaidBy(paidBy);
            expense.setGroupId(groupId);
            expense.setDescription(description);
            expense.setOwedBy(member);
            expenseTrackerRepository.save(expense);
        }
    }

    public List<Transaction> calculateBalancePerPerson() {
        HashMap<Long, Double> balancePerPerson = new HashMap<>();
        for (ExpenseTracker expenseTracker : expenseTrackerRepository.findAll()) {
            balancePerPerson.put(expenseTracker.getPaidBy(),
                    balancePerPerson.getOrDefault(expenseTracker.getPaidBy(), 0.0) + expenseTracker.getAmount());

            balancePerPerson.put(expenseTracker.getOwedBy(),
                    balancePerPerson.getOrDefault(expenseTracker.getOwedBy(), 0.0) - expenseTracker.getAmount());
        }

        PriorityQueue<Pair> recievers = new PriorityQueue<Pair>(Collections.reverseOrder());    // max-heap
        PriorityQueue<Pair> givers = new PriorityQueue<Pair>();  // min heap

        for(var v: balancePerPerson.keySet()) {
            if (balancePerPerson.get(v) >= 0) {
                recievers.add(new Pair(userRepository.findById(v).get().getName(), balancePerPerson.get(v)));
            }
            else {
                givers.add(new Pair(userRepository.findById(v).get().getName(), balancePerPerson.get(v)));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while(!recievers.isEmpty() && !givers.isEmpty()) {
            Pair creditor = recievers.poll();
            Pair debtor = givers.poll();

            double amountToSettle = Math.min(creditor.amount, Math.abs(debtor.amount));

            transactions.add(new Transaction(debtor.member, creditor.member, amountToSettle));

            if (creditor.amount > amountToSettle) {
                recievers.offer(new Pair(creditor.member, creditor.amount - amountToSettle));
            }
            if (Math.abs(debtor.amount) > amountToSettle) {
                givers.offer(new Pair(debtor.member, debtor.amount + amountToSettle));
            }
        }

        return transactions;
    }
}


