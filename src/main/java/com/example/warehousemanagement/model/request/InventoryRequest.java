package com.example.warehousemanagement.model.request;

import lombok.Data;

@Data
public class InventoryRequest {
    private Long productId;

    private Long branchId = 0L;

    private int quantity;

    private Long idCategory;

    private String name;

    private String image;

    private Double price;

    private int size;

    private Boolean isWarehouse;
}
