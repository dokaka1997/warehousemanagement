package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Warehouse;
import com.example.warehousemanagement.model.request.AddWarehouseRequest;
import com.example.warehousemanagement.model.response.ImportWarehouseResponse;
import com.example.warehousemanagement.model.response.ListProductWarehouseResponse;

public interface WarehouseService {

    ImportWarehouseResponse addNewWarehouse(AddWarehouseRequest warehouse);

    ListProductWarehouseResponse getListProductOfWarehouse(int pageIndex, int pageSize, Long branchId, String name, int size, Long category);
}
