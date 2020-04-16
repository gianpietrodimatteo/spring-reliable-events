package com.example.reliableevents.controller;

import com.example.reliableevents.ExpenseService;
import com.example.reliableevents.domain.Expense;
import com.example.reliableevents.dto.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping()
    public String addExpense(@RequestBody ExpenseDto expenseDto) {
        expenseService.save(expenseDto);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public List<Expense> getAllUsers() {
        return expenseService.findAll();
    }
}