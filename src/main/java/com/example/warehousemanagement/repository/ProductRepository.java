package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdBranch(Long idBranch);

    List<Product> findAllByIdCategory(Long category);
}
