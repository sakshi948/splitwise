package com.example.splitwise.controller;

import com.example.splitwise.DTO.AddExpenseRequest;
import com.example.splitwise.models.ExpenseTracker;
import com.example.splitwise.service.ExpenseTrackerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @GetMapping("/expense-per-person")
    public HashMap<Long, Double> getExpensePerPerson(@RequestParam Integer groupId) {
        return expenseTrackerService.getExpensePerPerson(groupId);
    }
}
