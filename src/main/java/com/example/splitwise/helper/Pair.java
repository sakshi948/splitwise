package com.example.splitwise.helper;

public class Pair implements Comparable<Pair> {
    public String member;
    public Double amount;

    public Pair(String member, Double amount) {
        this.member = member;
        this.amount = amount;
    }

    @Override
    public int compareTo(Pair other) {
        return this.amount.compareTo(other.amount);
    }

    @Override
    public String toString() {
        return "(" + member + ", " + amount + ")";
    }
}
