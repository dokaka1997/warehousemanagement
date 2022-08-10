package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Inventory;
import com.example.warehousemanagement.model.response.ListProductBranchResponse;
import com.example.warehousemanagement.repository.InventoryRepository;
import com.example.warehousemanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    InventoryRepository inventoryRepository;


    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public ListProductBranchResponse getListProductOfBranch(int pageIndex, int pageSize, Long branchId, String name, int size, Long category) {
        ListProductBranchResponse listProductBranchResponse = new ListProductBranchResponse();
        List<Inventory> inventories;
        if (size == -1 && category == -1) {
            inventories = inventoryRepository.findAllByBranchIdAndNameContaining(branchId, name, PageRequest.of(pageIndex, pageSize));
        } else {

            if (size == -1) {
                inventories = inventoryRepository.findAllByBranchIdAndNameContainingAndIdCategory(branchId, name, category, PageRequest.of(pageIndex, pageSize));
            } else if (category == -1) {
                inventories = inventoryRepository.findAllByBranchIdAndNameContainingAndSize(branchId, name, size, PageRequest.of(pageIndex, pageSize));
            } else {
                inventories = inventoryRepository.findAllByBranchIdAndNameContainingAndIdCategoryAndSize(branchId, name, category, size, PageRequest.of(pageIndex, pageSize));
            }
        }
        listProductBranchResponse.setProducts(inventories);
        listProductBranchResponse.setTotal(inventories.size());
        return listProductBranchResponse;
    }

    @Override
    public Inventory addNewInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Boolean deleteInventoryById(Long id) {
        try {
            inventoryRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
