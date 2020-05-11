package com.example.reliableevents.service;

import com.example.reliableevents.domain.Expense;
import com.example.reliableevents.dto.ExpenseDto;
import com.example.reliableevents.operation.entity.domain.ExpenseOperation;
import com.example.reliableevents.operation.entity.event.ExpenseOperationCommitEvent;
import com.example.reliableevents.operation.entity.event.ExpenseOperationEvent;
import com.example.reliableevents.operation.entity.repository.ExpenseOperationRepository;
import com.example.reliableevents.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {
    private final static Logger logger = LoggerFactory.getLogger(ExpenseService.class);
    private final ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseOperationRepository expenseOperationRepository;
    @Autowired
    private SecurityService securityService;

    public ExpenseService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void save(ExpenseDto expenseDto) {
        logger.info("Saving " + expenseDto);
        Expense expense = new Expense(expenseDto);
        expenseRepository.save(expense);
        ExpenseOperation expenseOperation = expenseOperationRepository.save(createOperation(expense));
        applicationEventPublisher.publishEvent(new ExpenseOperationCommitEvent(expenseOperation));
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Transactional
    public void updateById(Integer id, ExpenseDto expenseDto) {
        logger.info("Updating " + id + " " + expenseDto);
        Expense expense = expenseRepository.getOne(id);
        ExpenseOperation expenseOperation = expenseOperationRepository.save(updateOperation(expense, expenseDto));
        expense.setName(expenseDto.getName());
        expense.setBudget(expenseDto.getBudget());
        expenseRepository.save(expense);
        applicationEventPublisher.publishEvent(new ExpenseOperationCommitEvent(expenseOperation));
    }

    public void deleteById(Integer id) {
        logger.info("Deleting " + id);
        Expense expense = expenseRepository.getOne(id);
        expenseRepository.deleteById(id);
        ExpenseOperation expenseOperation = expenseOperationRepository.save(deleteOperation(expense));
        applicationEventPublisher.publishEvent(new ExpenseOperationEvent(expenseOperation));
    }

    private ExpenseOperation createOperation(Expense expense) {
        return new ExpenseOperation(expense.getName(), expense.getBudget());
    }

    private ExpenseOperation deleteOperation(Expense expense) {
        BigDecimal budgetChange = securityService.decryptBigDecimal(expense.getBudget()).multiply(new BigDecimal(-1));
        return new ExpenseOperation(expense.getName(), securityService.encrypt(budgetChange));
    }

    private ExpenseOperation updateOperation(Expense oldExpense, ExpenseDto newExpense) {
        BigDecimal oldBudget = securityService.decryptBigDecimal(oldExpense.getBudget());
        BigDecimal newBudget = securityService.decryptBigDecimal(newExpense.getBudget());
        BigDecimal budgetChange = newBudget.subtract(oldBudget);
        return new ExpenseOperation(oldExpense.getName(), securityService.encrypt(budgetChange));
    }
}
