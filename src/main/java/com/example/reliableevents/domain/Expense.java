package com.example.reliableevents.domain;

import com.example.reliableevents.dto.ExpenseDto;

import javax.persistence.*;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    private byte[] budget;

    public Expense() {
    }

    public Expense(ExpenseDto expenseDto) {
        this.name = expenseDto.getName();
        this.budget = expenseDto.getBudget();
    }

    public Expense(String name, byte[] budget) {
        this.name = name;
        this.budget = budget;
    }

    public Expense(Expense expense) {
        this.id = expense.id;
        this.name = expense.name;
        this.budget = expense.budget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
