package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.DeleteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeleteHistoryRepository extends JpaRepository<DeleteHistory, Long> {

    List<DeleteHistory> findAllByBranchId(Long branchId);
}
