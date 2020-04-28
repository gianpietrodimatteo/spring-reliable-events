package com.example.reliableevents.operation;

import com.example.reliableevents.operation.core.event.DashboardStatusEvent;
import com.example.reliableevents.operation.entity.event.ExpenseOperationCommitEvent;
import com.example.reliableevents.operation.entity.event.ExpenseOperationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.ExecutionException;

@Component
public class OperationEventListener {
    private static final Logger logger = LoggerFactory.getLogger(OperationEventListener.class);

    @Autowired
    private DashboardUpdateService dashboardUpdateService;

    @Async
    @TransactionalEventListener
    public void sendExpenseOperationAfterCommit(ExpenseOperationCommitEvent event) throws ExecutionException, InterruptedException {
        dashboardUpdateService.sendExpenseOperation(event.getOperation());
    }

    @Async
    @EventListener
    public void sendExpenseOperationScheduled(ExpenseOperationEvent event) throws ExecutionException, InterruptedException {
        dashboardUpdateService.sendExpenseOperation(event.getOperation());
    }

    @Async
    @EventListener
    public void checkDashboardStatus(DashboardStatusEvent event) throws ExecutionException, InterruptedException {
        if (event.isWalletDashboardUp())
            return;
        dashboardUpdateService.checkDashboardStatus();
    }


}
