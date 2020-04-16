package com.example.reliableevents;

import com.example.reliableevents.domain.Expense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class WalletDashboardUpdateService {
    private static final Logger logger = LoggerFactory.getLogger(WalletDashboardUpdateService.class);

    private final RestTemplate restTemplate;

    public WalletDashboardUpdateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    @TransactionalEventListener
    public CompletableFuture<ResponseEntity<String>> updateExpense(ExpenseUpdateEvent expenseUpdateEvent) throws InterruptedException, ExecutionException {
        final Expense expense = expenseUpdateEvent.getExpense();
        logger.info("Sending expense to dashboard " + expense);
        String url = String.format("http://localhost:3000/expense");
        ResponseEntity<String> result;
        try {
            result = restTemplate.postForEntity(url, expense, String.class);
        } catch (ResourceAccessException e) {
            logger.warn("The wallet dashboard server is down!");
            return null;
        }
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        CompletableFuture<ResponseEntity<String>> trueResult = CompletableFuture.completedFuture(result);
        logger.info(String.valueOf(trueResult.get().getStatusCodeValue()));
        return trueResult;
    }
}
