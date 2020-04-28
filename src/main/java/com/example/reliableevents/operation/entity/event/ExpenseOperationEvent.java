package com.example.reliableevents.operation.entity.event;

import com.example.reliableevents.operation.core.event.OperationBaseEvent;
import com.example.reliableevents.operation.entity.domain.ExpenseOperation;

public class ExpenseOperationEvent extends OperationBaseEvent<ExpenseOperation> {

    public ExpenseOperationEvent() {
    }

    public ExpenseOperationEvent(ExpenseOperation expenseOperation) {
        super(expenseOperation);
    }
}
