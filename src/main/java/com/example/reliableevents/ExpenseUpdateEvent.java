package com.example.reliableevents;

import com.example.reliableevents.domain.Expense;

public class ExpenseUpdateEvent {
    private final Expense expense;

    public ExpenseUpdateEvent(Expense expense){
        this.expense = expense;
    }

    public Expense getExpense() {
        return expense;
    }

}
