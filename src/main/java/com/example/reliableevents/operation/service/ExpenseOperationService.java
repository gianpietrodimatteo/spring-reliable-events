package com.example.reliableevents.operation.service;

import com.example.reliableevents.operation.core.enums.Status;
import com.example.reliableevents.operation.core.service.OperationService;
import com.example.reliableevents.operation.domain.ExpenseOperation;
import com.example.reliableevents.operation.event.ExpenseOperationEvent;
import com.example.reliableevents.operation.repository.ExpenseOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseOperationService extends OperationService<ExpenseOperation> {
    private final static Logger logger = LoggerFactory.getLogger(ExpenseOperationService.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ExpenseOperationRepository expenseOperationRepository;

    public ExpenseOperationService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendEvents() {
        logger.debug("Shooting saved expense operations scheduled events.");
        super.sendEvents();
    }

    protected void publish(ExpenseOperation operation) {
        applicationEventPublisher.publishEvent(new ExpenseOperationEvent(operation));
    }

    protected List<ExpenseOperation> listPending(int limit) {
        Pageable topPage = PageRequest.of(0, limit);
        return expenseOperationRepository.findByStatus(Status.PENDING, topPage);
    }

}
