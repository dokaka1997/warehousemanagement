package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.PositionWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionWarehouseRepository extends JpaRepository<PositionWarehouse, Long> {
    List<PositionWarehouse> findAllByWarehouseId(Long warehouseId);

    PositionWarehouse findFirstByWarehouseId(Long warehouseId);
    
}
