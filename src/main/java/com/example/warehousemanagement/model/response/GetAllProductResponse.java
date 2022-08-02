package com.example.warehousemanagement.model.response;

import com.example.warehousemanagement.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class GetAllProductResponse {

    private int total;

    private List<Product> products;
}
