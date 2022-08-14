package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Product;
import com.example.warehousemanagement.model.request.ExportProductRequest;
import com.example.warehousemanagement.model.response.GetAllProductResponse;
import com.example.warehousemanagement.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    Product addNewProduct(Product product);

    GetAllProductResponse getAllProduct(String name, int size, Long category, int pageIndex, int pageSize);

    ProductResponse getById(Long id);

    List<Product> getAllProductByCategory(Long category);

    List<Product> getAllProductByBranch(Long branch);

    Boolean deleteProductById(Long id);

    ExportProductRequest addNewExport(ExportProductRequest exportProductRequest) throws Exception;


}
