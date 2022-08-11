package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findFirstByProductId(Long productId);
}
