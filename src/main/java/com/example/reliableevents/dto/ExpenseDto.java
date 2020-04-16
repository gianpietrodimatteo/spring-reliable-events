package com.example.reliableevents.dto;

import com.example.reliableevents.domain.Expense;

import java.math.BigDecimal;

public class ExpenseDto {
    private String name;

    private BigDecimal budget;

    public ExpenseDto() {
    }

    public ExpenseDto(Expense expense) {
        this.name = expense.getName();
        this.budget = expense.getBudget();
    }

    public ExpenseDto(String name, BigDecimal budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
