package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Inventory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findAllByBranchIdAndNameContaining(Long id, String name, Pageable pageable);

    List<Inventory> findAllByBranchIdAndNameContaining(Long id, String name);

    List<Inventory> findAllByBranchIdAndNameContainingAndSize(Long id, String name, int size, Pageable pageable);

    List<Inventory> findAllByBranchIdAndNameContainingAndSize(Long id, String name, int size);

    List<Inventory> findAllByBranchIdAndNameContainingAndIdCategory(Long id, String name, Long category, Pageable pageable);

    List<Inventory> findAllByBranchIdAndNameContainingAndIdCategory(Long id, String name, Long category);


    List<Inventory> findAllByBranchIdAndNameContainingAndIdCategoryAndSize(Long id, String name, Long category, int size, Pageable pageable);

    List<Inventory> findAllByBranchIdAndNameContainingAndIdCategoryAndSize(Long id, String name, Long category, int size);

    Inventory getFirstByProductIdAndBranchId(Long productId, Long branchId);
}
