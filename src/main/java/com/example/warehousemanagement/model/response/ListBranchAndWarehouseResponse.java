package com.example.warehousemanagement.model.response;

import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.entity.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class ListBranchAndWarehouseResponse {
    private List<Warehouse> warehouses;

    private List<Branch> branches;
}
