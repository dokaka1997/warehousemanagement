package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.DeleteHistory;
import com.example.warehousemanagement.entity.Inventory;
import com.example.warehousemanagement.model.request.DeleteProductInventoryRequest;
import com.example.warehousemanagement.model.request.InventoryRequest;
import com.example.warehousemanagement.model.response.ListProductInventoryResponse;

import java.util.List;

public interface InventoryService {

    ListProductInventoryResponse getListProductOfBranch(int pageIndex, int pageSize, Long branchId, String name, int size, Long category);

    Inventory addNewInventory(InventoryRequest inventory);

    Boolean deleteInventoryById(DeleteProductInventoryRequest deleteProductInventoryRequest);

    List<DeleteHistory> viewDeleteHistory(Long id);

}
