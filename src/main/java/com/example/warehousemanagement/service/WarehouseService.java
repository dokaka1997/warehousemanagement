package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.response.ListProductWarehouseResponse;
import com.example.warehousemanagement.model.response.ProductWarehouseResponse;

import java.util.List;

public interface WarehouseService {
    ListProductWarehouseResponse getAllProduct(int pageIndex, int pageSize);
}
