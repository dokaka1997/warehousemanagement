package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.PositionBranch;
import com.example.warehousemanagement.entity.PositionWarehouse;
import com.example.warehousemanagement.repository.PositionBranchRepository;
import com.example.warehousemanagement.repository.PositionWarehouseRepository;
import com.example.warehousemanagement.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    PositionBranchRepository positionBranchRepository;

    PositionWarehouseRepository positionWarehouseRepository;

    @Autowired
    public PositionServiceImpl(PositionBranchRepository positionBranchRepository, PositionWarehouseRepository positionWarehouseRepository) {
        this.positionBranchRepository = positionBranchRepository;
        this.positionWarehouseRepository = positionWarehouseRepository;
    }

    @Override
    public List<PositionBranch> getPositionBranchById(Long branchId) {
        return positionBranchRepository.findAllByBranchId(branchId);
    }

    @Override
    public List<PositionWarehouse> getPositionWarehouseById(Long warehouseId) {
        return positionWarehouseRepository.findAll();
    }
}
