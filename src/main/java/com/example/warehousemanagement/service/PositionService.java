package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.PositionBranch;
import com.example.warehousemanagement.entity.PositionWarehouse;

import java.util.List;

public interface PositionService {
    List<PositionBranch> getPositionBranchById(Long branchId);

    List<PositionWarehouse> getPositionWarehouseById(Long warehouseId);

}
