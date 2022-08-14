package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.PositionBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionBranchRepository extends JpaRepository<PositionBranch, Long> {
    List<PositionBranch> findAllByBranchId(Long branchId);

}
