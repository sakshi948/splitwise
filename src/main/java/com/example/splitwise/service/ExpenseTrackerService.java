package com.example.splitwise.service;

import com.example.splitwise.models.ExpensePerPerson;
import com.example.splitwise.models.ExpenseTracker;
import com.example.splitwise.repository.ExpensePerPersonRepository;
import com.example.splitwise.repository.ExpenseTrackerRepositroy;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ExpenseTrackerService {
    private final ExpenseTrackerRepositroy expenseTrackerRepository;
    private final ExpensePerPersonRepository expensePerPersonRepository;
    ExpenseTrackerService(ExpenseTrackerRepositroy expenseTrackerRepository, ExpensePerPersonRepository expensePerPersonRepository) {
        this.expenseTrackerRepository = expenseTrackerRepository;
        this.expensePerPersonRepository = expensePerPersonRepository;
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

    public HashMap<Long, Double> getExpensePerPerson(Integer groupId) {
        HashMap<Long, Double> expensePerPerson = new HashMap<>();
        for(ExpensePerPerson person : expensePerPersonRepository.findAll()) {
            System.out.println(person);
            expensePerPerson.put(person.getUser_id(), person.getExpense());
        }
        return expensePerPerson;
    }
}
