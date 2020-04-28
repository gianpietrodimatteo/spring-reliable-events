package com.example.reliableevents.service;

import com.example.reliableevents.domain.Expense;
import com.example.reliableevents.dto.ExpenseDto;
import com.example.reliableevents.operation.domain.ExpenseOperation;
import com.example.reliableevents.operation.event.ExpenseOperationCommitEvent;
import com.example.reliableevents.operation.event.ExpenseOperationEvent;
import com.example.reliableevents.operation.repository.ExpenseOperationRepository;
import com.example.reliableevents.repository.ExpenseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseService {
    private final static Logger logger = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    private final ObjectMapper eventSerializer;
    @Autowired
    private ExpenseOperationRepository expenseOperationRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public ExpenseService(ObjectMapper eventSerializer, ApplicationEventPublisher applicationEventPublisher) {
        this.eventSerializer = eventSerializer;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void save(ExpenseDto expenseDto) {
        logger.info("Saving " + expenseDto);
        Expense expense = new Expense(expenseDto);
        expenseRepository.save(expense);
        ExpenseOperation expenseOperation = expenseOperationRepository.save(new ExpenseOperation(expense, false));
        applicationEventPublisher.publishEvent(new ExpenseOperationCommitEvent(expenseOperation));
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Transactional
    public void updateById(Integer id, ExpenseDto expenseDto) {
        logger.info("Updating " + id + " " + expenseDto);
        Expense expense = expenseRepository.getOne(id);
        ExpenseOperation expenseOperation = expenseOperationRepository.save(new ExpenseOperation(expense, expenseDto));
        expense.setName(expenseDto.getName());
        expense.setBudget(expenseDto.getBudget());
        expenseRepository.save(expense);
        applicationEventPublisher.publishEvent(new ExpenseOperationCommitEvent(expenseOperation));
    }

    public void deleteById(Integer id) {
        logger.info("Deleting " + id);
        Expense expense = expenseRepository.getOne(id);
        expenseRepository.deleteById(id);
        ExpenseOperation expenseOperation = expenseOperationRepository.save(new ExpenseOperation(expense, true));
        applicationEventPublisher.publishEvent(new ExpenseOperationEvent(expenseOperation));
    }
}
