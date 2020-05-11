package com.example.reliableevents.dto;

import com.example.reliableevents.domain.Expense;

public class ExpenseDto {
    private String name;

    private byte[] budget;

    public ExpenseDto() {
    }

    public ExpenseDto(Expense expense) {
        this.name = expense.getName();
        this.budget = expense.getBudget();
    }

    public ExpenseDto(String name, byte[] budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBudget() {
        return budget;
    }

    public void setBudget(byte[] budget) {
        this.budget = budget;
    }
}
