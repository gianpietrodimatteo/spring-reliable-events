package com.example.reliableevents.controller;

import com.example.reliableevents.domain.Expense;
import com.example.reliableevents.dto.ExpenseDto;
import com.example.reliableevents.service.ExpenseService;
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

    @PutMapping("/:id")
    public String updateExpense(@RequestParam Integer id, @RequestBody ExpenseDto expenseDto) {
        expenseService.updateById(id, expenseDto);
        return "Updated";
    }

    @DeleteMapping("/:id")
    public String deleteExpense(@RequestParam Integer id) {
        expenseService.deleteById(id);
        return "Deleted";
    }

    @GetMapping()
    public List<Expense> getAllUsers() {
        return expenseService.findAll();
    }
}