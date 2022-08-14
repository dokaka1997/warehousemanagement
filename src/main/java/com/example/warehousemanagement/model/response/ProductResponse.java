package com.example.warehousemanagement.model.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long idBranch;

    private Long idCategory;

    private Long warehouseId;

    private String name;

    private String image;

    private Double price;

    private int size;

    private String sku;

    private String positionName;
}
