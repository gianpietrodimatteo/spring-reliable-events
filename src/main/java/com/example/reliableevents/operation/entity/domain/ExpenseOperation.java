package com.example.reliableevents.operation.entity.domain;

import com.example.reliableevents.domain.Expense;
import com.example.reliableevents.dto.ExpenseDto;
import com.example.reliableevents.operation.core.domain.Operation;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class ExpenseOperation extends Operation {
    private String name;
    private BigDecimal budgetChange;

    public ExpenseOperation() {
    }

    public ExpenseOperation(String name, BigDecimal budgetChange) {
        super();
        this.name = name;
        this.budgetChange = budgetChange;
    }

    public ExpenseOperation(Expense expense, boolean delete) {
        super();
        this.name = expense.getName();
        this.budgetChange = !delete ? expense.getBudget() : expense.getBudget().multiply(BigDecimal.valueOf(-1));
    }

    public ExpenseOperation(Expense oldExpense, ExpenseDto newExpense) {
        super();
        this.name = oldExpense.getName();
        this.budgetChange = newExpense.getBudget().subtract(oldExpense.getBudget());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBudgetChange() {
        return budgetChange;
    }

    public void setBudgetChange(BigDecimal budgetChange) {
        this.budgetChange = budgetChange;
    }
}
