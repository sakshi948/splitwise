package com.example.splitwise.controller;

import com.example.splitwise.DTO.AddExpenseRequest;
import com.example.splitwise.service.ExpenseTrackerService;
import com.example.splitwise.helper.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense-tracker")
public class ExpenseTrackerController {
    private final ExpenseTrackerService expenseTrackerService;

    public ExpenseTrackerController(ExpenseTrackerService expenseTrackerService) {
        this.expenseTrackerService = expenseTrackerService;
    }

    @PostMapping("/add-expense")
    public void addExpense(@RequestBody AddExpenseRequest addExpenseRequest) {
        expenseTrackerService.addExpense(addExpenseRequest.getGroupId(), addExpenseRequest.getAmount(), addExpenseRequest.getPaidBy(), addExpenseRequest.getMembers(), addExpenseRequest.getDescription());
    }

    @GetMapping("/balance-per-person")
    public List<Transaction> balancePerPerson() {
        return expenseTrackerService.calculateBalancePerPerson();
    }
}
