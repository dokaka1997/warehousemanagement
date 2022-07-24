package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Product;

import java.util.List;

public interface ProductService {

    Product addNewProduct(Product product);

    List<Product> getAllProduct(int pageIndex, int pageSize);

    Product getById(Long id);

    List<Product> getAllProductByCategory(Long category);

    List<Product> getAllProductByBranch(Long branch);

    Boolean deleteProductById(Long id);


}
