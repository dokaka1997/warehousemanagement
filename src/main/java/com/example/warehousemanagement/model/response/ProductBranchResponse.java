package com.example.warehousemanagement.model.response;

import lombok.Data;

@Data
public class ProductBranchResponse {
    private Long productId;

    private String name;

    private String image;

    private Double price;

    private int size;

    private String position;

    private int quantity;
}
