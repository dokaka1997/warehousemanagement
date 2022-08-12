package com.example.warehousemanagement.model.request;

import lombok.Data;

@Data
public class AddWarehouseRequest {
    private Long productId;

    private int quantity;

    private Long positionId;
}
