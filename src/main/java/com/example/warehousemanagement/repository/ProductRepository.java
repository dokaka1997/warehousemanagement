package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdBranch(Long idBranch);

    List<Product> findAllByIdCategory(Long category);

    List<Product> findAllByNameContaining(String name, Pageable pageable);

    List<Product> findAllByNameContainingAndSizeIs(String name, int size, Pageable pageable);

    List<Product> findAllByNameContainingAndAndIdCategoryIs(String name, Long id, Pageable pageable);

    List<Product> findAllByNameContainingAndAndIdCategoryIsAndSizeIs(String name, Long id, int size, Pageable pageable);
}
