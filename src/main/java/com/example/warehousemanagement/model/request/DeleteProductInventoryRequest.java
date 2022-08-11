package com.example.warehousemanagement.model.request;

import lombok.Data;

@Data
public class DeleteProductInventoryRequest {

    private Long id;

    private Long branchId;

    private int quantity;

    private Long accountId;

    private String accountName;

    private String reason;
}
