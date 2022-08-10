package com.example.warehousemanagement.model.response;

import lombok.Data;

@Data
public class WarehouseResponse {
    private Long id;

    private String name;

    private String image;

    private Double price;

    private int size;

    private String sku;

    private String position;

    private int quantity;
}
