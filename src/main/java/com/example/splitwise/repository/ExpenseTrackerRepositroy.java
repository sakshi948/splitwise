package com.example.splitwise.repository;

import com.example.splitwise.models.ExpenseTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseTrackerRepositroy extends JpaRepository<ExpenseTracker, Integer> {
    @Query("SELECT e FROM ExpenseTracker e WHERE e.groupId = :groupId AND e.paidBy = :paidBy AND e.owedBy = :owedBy")
    ExpenseTracker findByGroupIdAndPaidByAndOwedBy(@Param("groupId") Integer groupId,
                                                   @Param("paidBy") Integer paidBy,
                                                   @Param("owedBy") Integer owedBy);
}
