package com.example.reliableevents.operation;

import com.example.reliableevents.operation.core.event.DashboardStatusEvent;
import com.example.reliableevents.operation.entity.service.ExpenseOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OperationScheduler {
    private final static Logger logger = LoggerFactory.getLogger(OperationScheduler.class);
    private final ApplicationEventPublisher applicationEventPublisher;
    private Boolean dashboardServerIsUp;

    @Autowired
    private ExpenseOperationService expenseOperationService;

    public OperationScheduler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.dashboardServerIsUp = true;
    }

    @Scheduled(initialDelayString = "${operation.schedule.delay}", fixedRateString = "${operation.schedule.delay}")
    public void sendAllEvents() {
        logger.debug("Shooting scheduled events.");
        if (dashboardServerIsUp) {
            expenseOperationService.sendEvents();
        } else {
            applicationEventPublisher.publishEvent(new DashboardStatusEvent(false));
        }
    }

    @EventListener
    public void dashboardStatusUpdate(DashboardStatusEvent event) {
        this.dashboardServerIsUp = event.isWalletDashboardUp();
    }

}
