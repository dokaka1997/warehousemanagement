package com.example.warehousemanagement.model.response;

import lombok.Data;

@Data
public class ProductWarehouseResponse {
    
    private Long productId;

    private int quantity;

    private String image;

}
