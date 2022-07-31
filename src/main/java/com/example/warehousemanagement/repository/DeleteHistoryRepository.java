package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.DeleteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteHistoryRepository extends JpaRepository<DeleteHistory, Long> {
}
