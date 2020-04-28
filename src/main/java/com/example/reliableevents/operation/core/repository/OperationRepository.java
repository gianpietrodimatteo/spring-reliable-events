package com.example.reliableevents.operation.core.repository;

import com.example.reliableevents.operation.core.enums.Status;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationRepository<EntityOperation> {

    List<EntityOperation> findByStatus(Status status, Pageable pageable);

}
