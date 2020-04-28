package com.example.reliableevents.operation.event;

import com.example.reliableevents.operation.core.event.OperationBaseEvent;
import com.example.reliableevents.operation.domain.ExpenseOperation;

public class ExpenseOperationCommitEvent extends OperationBaseEvent<ExpenseOperation> {
    public ExpenseOperationCommitEvent() {
    }

    public ExpenseOperationCommitEvent(ExpenseOperation expenseOperation) {
        super(expenseOperation);
    }
}
