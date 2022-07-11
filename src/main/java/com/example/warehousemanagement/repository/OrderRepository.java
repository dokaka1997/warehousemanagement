package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllByBranchId(Long id);

}
