package com.example.splitwise.service;

import com.example.splitwise.models.ExpenseTracker;
import com.example.splitwise.repository.ExpenseTrackerRepositroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTrackerService {
    private final ExpenseTrackerRepositroy expenseTrackerRepository;
    ExpenseTrackerService(ExpenseTrackerRepositroy expenseTrackerRepository) {
        this.expenseTrackerRepository = expenseTrackerRepository;
    }

    public void addExpense(Integer groupId, Double amount, Integer paidBy, List<Integer> members, String description) {
        double expensePerPerson = amount/members.size();
        for (Integer member : members) {
            ExpenseTracker existingExpense = expenseTrackerRepository
                    .findByGroupIdAndPaidByAndOwedBy(groupId, paidBy, member);

            if (existingExpense != null) {
                existingExpense.setAmount(existingExpense.getAmount() + expensePerPerson);
                expenseTrackerRepository.save(existingExpense);
            } else {
                ExpenseTracker expense = new ExpenseTracker();
                expense.setAmount(expensePerPerson);
                expense.setPaidBy(paidBy);
                expense.setGroupId(groupId);
                expense.setDescription(description);
                expense.setOwedBy(member);
                expenseTrackerRepository.save(expense);
            }
        }
    }
}
