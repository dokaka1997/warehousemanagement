package com.example.warehousemanagement.model.response;

import lombok.Data;

@Data
public class ImportWarehouseResponse {
    private Long id;

    private Long productId;

    private int quantity;

    private Long positionId;
}
