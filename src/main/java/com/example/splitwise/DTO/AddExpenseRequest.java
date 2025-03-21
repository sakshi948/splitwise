package com.example.splitwise.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class AddExpenseRequest {
    private Integer groupId;
    private Double amount;
    private Integer paidBy;
    private List<Long> members;
    private String description;
}
