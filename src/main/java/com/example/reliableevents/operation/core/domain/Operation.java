package com.example.reliableevents.operation.core.domain;

import com.example.reliableevents.operation.core.enums.Status;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class Operation {
    private final String uuid = UUID.randomUUID().toString();
    private final LocalDateTime occurredAt = LocalDateTime.now();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDateTime receivedAt;
    private Status status = Status.PENDING;

    public void received(LocalDateTime at) {
        this.receivedAt = at;
        this.status = Status.RECEIVED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public Status getStatus() {
        return status;
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

}
