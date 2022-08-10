package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Inventory;
import com.example.warehousemanagement.model.response.ListProductBranchResponse;

public interface InventoryService {

    ListProductBranchResponse getListProductOfBranch(int pageIndex, int pageSize, Long branchId, String name, int size, Long category);

    Inventory addNewInventory(Inventory inventory);

    Boolean deleteInventoryById(Long id);

}
