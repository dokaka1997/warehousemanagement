package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.DeleteHistory;
import com.example.warehousemanagement.entity.Inventory;
import com.example.warehousemanagement.model.request.DeleteProductInventoryRequest;
import com.example.warehousemanagement.model.response.ListProductBranchResponse;

import java.util.List;

public interface InventoryService {

    ListProductBranchResponse getListProductOfBranch(int pageIndex, int pageSize, Long branchId, String name, int size, Long category);

    Inventory addNewInventory(Inventory inventory);

    Boolean deleteInventoryById(DeleteProductInventoryRequest deleteProductInventoryRequest);

    List<DeleteHistory> viewDeleteHistory(Long id);

}
