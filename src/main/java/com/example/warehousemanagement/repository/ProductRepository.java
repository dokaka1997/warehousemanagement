package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdBranch(Long idBranch);
    Product getFirstBySku(String sku);

    List<Product> findAllByWarehouseId(Long warehouse);

    List<Product> findAllByIdCategory(Long category);

    List<Product> findAllByNameContaining(String name, Pageable pageable);

    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByNameContainingAndSizeIs(String name, int size, Pageable pageable);

    List<Product> findAllByNameContainingAndSizeIs(String name, int size);

    List<Product> findAllByNameContainingAndIdCategoryIs(String name, Long id, Pageable pageable);

    List<Product> findAllByNameContainingAndIdCategoryIs(String name, Long id);

    List<Product> findAllByNameContainingAndIdCategoryIsAndSizeIs(String name, Long id, int size, Pageable pageable);

    List<Product> findAllByNameContainingAndIdCategoryIsAndSizeIs(String name, Long id, int size);

    List<Product> findAllByWarehouseIdAndNameContaining(Long warehouse, String name, Pageable pageable);

    List<Product> findAllByWarehouseIdAndNameContainingAndIdCategory(Long warehouse, String name, Long category, Pageable pageable);

    List<Product> findAllByWarehouseIdAndNameContainingAndSize(Long warehouse, String name, int size, Pageable pageable);

    List<Product> findAllByWarehouseIdAndNameContainingAndIdCategoryAndSize(Long warehouse, String name, Long category, int size, Pageable pageable);
}
