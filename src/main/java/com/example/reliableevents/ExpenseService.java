package com.example.reliableevents;

import com.example.reliableevents.domain.Expense;
import com.example.reliableevents.dto.ExpenseDto;
import com.example.reliableevents.repository.ExpenseRepository;
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

    private final ApplicationEventPublisher applicationEventPublisher;

    public ExpenseService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Transactional
    public void save(ExpenseDto expenseDto) {
        logger.info("Saving " + expenseDto);
        Expense expense = new Expense(expenseDto);
        expenseRepository.save(expense);
        final ExpenseUpdateEvent expenseUpdateEvent= new ExpenseUpdateEvent(expense);
        applicationEventPublisher.publishEvent(expenseUpdateEvent);
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

}
