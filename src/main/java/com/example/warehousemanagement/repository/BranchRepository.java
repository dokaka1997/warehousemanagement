package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
