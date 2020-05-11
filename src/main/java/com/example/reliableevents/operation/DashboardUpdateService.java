package com.example.reliableevents.operation;

import com.example.reliableevents.operation.core.dto.OperationDto;
import com.example.reliableevents.operation.core.event.DashboardStatusEvent;
import com.example.reliableevents.operation.entity.domain.ExpenseOperation;
import com.example.reliableevents.operation.entity.dto.ExpenseOperationDto;
import com.example.reliableevents.operation.entity.repository.ExpenseOperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class DashboardUpdateService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardUpdateService.class);
    private final RestTemplate restTemplate;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final String baseUrl = "http://localhost:3000/operation";
    private final Set<HttpStatus> acceptedCodes = new HashSet<>(Arrays.asList(HttpStatus.CREATED, HttpStatus.CONFLICT, HttpStatus.INTERNAL_SERVER_ERROR));
    @Autowired
    private ExpenseOperationRepository expenseOperationRepository;

    public DashboardUpdateService(RestTemplateBuilder restTemplateBuilder, ApplicationEventPublisher applicationEventPublisher) {
        this.restTemplate = restTemplateBuilder.build();
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendExpenseOperation(ExpenseOperation operation) throws ExecutionException, InterruptedException {
        final ExpenseOperationDto operationDto = new ExpenseOperationDto(operation);
        try {
            ResponseEntity<String> response = Objects.requireNonNull(send("expense", HttpMethod.POST, operationDto)).get();
            if (acceptedCodes.contains(response.getStatusCode())) {
                operation.received(LocalDateTime.now());
                expenseOperationRepository.save(operation);
                logger.info("Operation delivered! " + response.getStatusCode() + " " + operation);
            }
        } catch (ResourceAccessException e) {
            logger.warn("The dashboard server is down!");
            applicationEventPublisher.publishEvent(new DashboardStatusEvent(false));
        } catch (HttpClientErrorException e) {
            operation.received(LocalDateTime.now());
            expenseOperationRepository.save(operation);
            logger.info("Operation delivered! " + e.getMessage());
        }
    }

    public void checkDashboardStatus() throws ExecutionException, InterruptedException {
        try {
            ResponseEntity<String> response = Objects.requireNonNull(send("status", HttpMethod.GET, null)).get();
            if (!response.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                logger.info("The dashboard server is up again");
                applicationEventPublisher.publishEvent(new DashboardStatusEvent(true));
            }
        } catch (ResourceAccessException e) {
            logger.warn("The dashboard server is still down.");
        }
    }

    private CompletableFuture<ResponseEntity<String>> send(String relativeUrl, HttpMethod method, OperationDto operation) throws ResourceAccessException {
        final String url = baseUrl + "/" + relativeUrl;
        logger.info("Sending " + method.toString() + " to " + url);
        switch (method) {
            case GET:
                return CompletableFuture.completedFuture(restTemplate.getForEntity(url, String.class));
            case POST:
                return CompletableFuture.completedFuture(restTemplate.postForEntity(url, operation, String.class));
            default:
                logger.error("No other method for this service implemented for the dashboard service!");
                return null;
        }
    }

}
