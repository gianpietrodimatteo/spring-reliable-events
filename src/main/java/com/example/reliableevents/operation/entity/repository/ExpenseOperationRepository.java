package com.example.reliableevents.operation.entity.repository;

import com.example.reliableevents.operation.core.enums.Status;
import com.example.reliableevents.operation.core.repository.OperationRepository;
import com.example.reliableevents.operation.entity.domain.ExpenseOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseOperationRepository extends JpaRepository<ExpenseOperation, Integer>, OperationRepository<ExpenseOperation> {
    List<ExpenseOperation> findByStatus(Status status, Pageable pageable);
}
