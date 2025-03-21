package com.example.splitwise.helper;

import lombok.Data;

@Data
public class Transaction {
    private String from;
    private String to;
    private double amount;

    public Transaction(String from, String to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Person " + from + " pays " + amount + " to Person " + to;
    }
}
