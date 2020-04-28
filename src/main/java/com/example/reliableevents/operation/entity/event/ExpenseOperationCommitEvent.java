package com.example.reliableevents.operation.entity.event;

import com.example.reliableevents.operation.core.event.OperationBaseEvent;
import com.example.reliableevents.operation.entity.domain.ExpenseOperation;

public class ExpenseOperationCommitEvent extends OperationBaseEvent<ExpenseOperation> {
    public ExpenseOperationCommitEvent() {
    }

    public ExpenseOperationCommitEvent(ExpenseOperation expenseOperation) {
        super(expenseOperation);
    }
}
