package com.example.reliableevents.operation.entity.domain;

import com.example.reliableevents.operation.core.domain.Operation;

import javax.persistence.Entity;

@Entity
public class ExpenseOperation extends Operation {
    private String name;
    private byte[] budgetChange;

    public ExpenseOperation() {
    }

    public ExpenseOperation(String name, byte[] budgetChange) {
        super();
        this.name = name;
        this.budgetChange = budgetChange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBudgetChange() {
        return budgetChange;
    }

    public void setBudgetChange(byte[] budgetChange) {
        this.budgetChange = budgetChange;
    }
}
