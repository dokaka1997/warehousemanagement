package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Branch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findAllByNameContainingAndActiveIs(String name, boolean active, Pageable pageable);

    List<Branch> findAllByNameContaining(String name, Pageable pageable);

    List<Branch> findAllByActiveIs(boolean active);
}
