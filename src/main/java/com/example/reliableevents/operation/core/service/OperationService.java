package com.example.reliableevents.operation.core.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public abstract class OperationService<EntityOperation> {
    @Value("${operation.service.query.limit}")
    private int queryLimit;

    public void sendEvents() {
        listPending(queryLimit).forEach(this::publish);
    }

    protected abstract void publish(EntityOperation operation);

    protected abstract List<EntityOperation> listPending(int limit);

}
