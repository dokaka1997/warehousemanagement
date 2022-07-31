package com.example.warehousemanagement.model.request;

import lombok.Data;

@Data
public class ListProductExportRequest {

    private Long productId;

    private int quantity;

    private double price;

    private Long branchId;

}
