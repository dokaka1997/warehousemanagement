package com.example.warehousemanagement.model.response;

import com.example.warehousemanagement.entity.Inventory;
import lombok.Data;

import java.util.List;


@Data
public class ListProductInventoryResponse {
    private int total;

    List<Inventory> products;

}
