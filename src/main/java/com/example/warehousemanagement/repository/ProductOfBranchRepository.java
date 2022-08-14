package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.ProductOfBranch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOfBranchRepository extends JpaRepository<ProductOfBranch, Long> {

    ProductOfBranch getFirstByProductIdAndBranchId(Long productId, Long branchId);

    List<ProductOfBranch> findAllByBranchIdAndNameContaining(Long id, String name, Pageable pageable);

    List<ProductOfBranch> findAllByBranchIdAndNameContainingAndSize(Long id, String name, int size, Pageable pageable);

    List<ProductOfBranch> findAllByBranchIdAndNameContainingAndIdCategory(Long id, String name, Long category, Pageable pageable);

    List<ProductOfBranch> findAllByBranchIdAndNameContainingAndIdCategoryAndSize(Long id, String name, Long category, int size, Pageable pageable);
}
