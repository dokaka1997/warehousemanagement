package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.ExportDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportDetailRepository extends JpaRepository<ExportDetail, Long> {
}
