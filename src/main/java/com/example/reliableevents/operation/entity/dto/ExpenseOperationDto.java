package com.example.reliableevents.operation.entity.dto;

import com.example.reliableevents.operation.core.dto.OperationDto;
import com.example.reliableevents.operation.entity.domain.ExpenseOperation;

import java.time.LocalDateTime;

public class ExpenseOperationDto extends OperationDto {

    private String name;
    private byte[] budgetChange;

    public ExpenseOperationDto() {
    }

    public ExpenseOperationDto(String uuid, LocalDateTime occurredAt, String name, byte[] budgetChange) {
        super(uuid, occurredAt);
        this.name = name;
        this.budgetChange = budgetChange;
    }

    public ExpenseOperationDto(ExpenseOperation operation) {
        super(operation);
        this.name = operation.getName();
        this.budgetChange = operation.getBudgetChange();
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
