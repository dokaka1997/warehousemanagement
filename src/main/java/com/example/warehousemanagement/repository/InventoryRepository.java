package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
