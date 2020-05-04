package com.example.reliableevents.domain;

import com.example.reliableevents.dto.ExpenseDto;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    private BigDecimal budget;

    public Expense() {
    }

    public Expense(ExpenseDto expenseDto) {
        this.name = expenseDto.getName();
        this.budget = expenseDto.getBudget();
    }

    public Expense(String name, BigDecimal budget) {
        this.name = name;
        this.budget = budget;
    }

    public Expense(Expense expense) {
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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
