package com.example.splitwise.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddExpenseRequest {
    private Integer groupId;
    private Double amount;
    private Integer paidBy;
    private List<Integer> members;
    private String description;
}
