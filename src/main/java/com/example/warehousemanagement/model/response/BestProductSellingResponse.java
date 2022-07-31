package com.example.warehousemanagement.model.response;

import lombok.Data;

@Data
public class BestProductSellingResponse {
    private Long id;

    private String name;

    private String image;

    private Double price;

    private int size;

    private String position;

    private int quantity;
}
