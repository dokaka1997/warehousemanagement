package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Product;
import com.example.warehousemanagement.entity.Warehouse;
import com.example.warehousemanagement.model.response.ProductWarehouseResponse;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.repository.WarehouseRepository;
import com.example.warehousemanagement.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    WarehouseRepository warehouseRepository;

    ProductRepository productRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWarehouseResponse> getAllProduct() {
        List<ProductWarehouseResponse> productWarehouseResponses = new ArrayList<>();
        List<Warehouse> warehouses = warehouseRepository.findAll();

        for (Warehouse warehouse : warehouses) {
            Optional<Product> optionalProduct = productRepository.findById(warehouse.getProductId());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                ProductWarehouseResponse productWarehouseResponse = new ProductWarehouseResponse();
                productWarehouseResponse.setProductId(product.getId());
                productWarehouseResponse.setImage(product.getImage());
                productWarehouseResponse.setQuantity(warehouse.getQuantity());
                productWarehouseResponses.add(productWarehouseResponse);
            }
        }
        return productWarehouseResponses;
    }
}
