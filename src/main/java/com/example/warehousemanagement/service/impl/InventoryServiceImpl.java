package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.*;
import com.example.warehousemanagement.model.request.DeleteProductInventoryRequest;
import com.example.warehousemanagement.model.request.InventoryRequest;
import com.example.warehousemanagement.model.response.ListProductInventoryResponse;
import com.example.warehousemanagement.repository.*;
import com.example.warehousemanagement.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    InventoryRepository inventoryRepository;

    DeleteHistoryRepository deleteHistoryRepository;

    PositionBranchRepository positionBranchRepository;

    WarehouseRepository warehouseRepository;

    ProductOfBranchRepository productOfBranchRepository;

    ModelMapper mapper;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, DeleteHistoryRepository deleteHistoryRepository
            , PositionBranchRepository positionBranchRepository, WarehouseRepository warehouseRepository,
                                ProductOfBranchRepository productOfBranchRepository, ModelMapper mapper) {
        this.inventoryRepository = inventoryRepository;
        this.deleteHistoryRepository = deleteHistoryRepository;
        this.positionBranchRepository = positionBranchRepository;
        this.warehouseRepository = warehouseRepository;
        this.productOfBranchRepository = productOfBranchRepository;
        this.mapper = mapper;
    }


    @Override
    public ListProductInventoryResponse getListProductOfBranch(int pageIndex, int pageSize, Long branchId, String name, int size, Long category) {
        ListProductInventoryResponse listProductBranchResponse = new ListProductInventoryResponse();
        List<Inventory> inventories;
        if (size == -1 && category == -1) {
            inventories = inventoryRepository.findAllByBranchIdAndNameContaining(branchId, name, PageRequest.of(pageIndex, pageSize));
            listProductBranchResponse.setTotal(inventoryRepository.findAllByBranchIdAndNameContaining(branchId, name).size());
        } else {
            if (size == -1) {
                inventories = inventoryRepository.findAllByBranchIdAndNameContainingAndIdCategory(branchId, name, category, PageRequest.of(pageIndex, pageSize));
                listProductBranchResponse.setTotal(inventoryRepository.findAllByBranchIdAndNameContainingAndIdCategory(branchId, name, category).size());
            } else if (category == -1) {
                inventories = inventoryRepository.findAllByBranchIdAndNameContainingAndSize(branchId, name, size, PageRequest.of(pageIndex, pageSize));
                listProductBranchResponse.setTotal(inventoryRepository.findAllByBranchIdAndNameContainingAndSize(branchId, name, size).size());
            } else {
                inventories = inventoryRepository.findAllByBranchIdAndNameContainingAndIdCategoryAndSize(branchId, name, category, size, PageRequest.of(pageIndex, pageSize));
                listProductBranchResponse.setTotal(inventoryRepository.findAllByBranchIdAndNameContainingAndIdCategoryAndSize(branchId, name, category, size).size());
            }
        }
        listProductBranchResponse.setProducts(inventories);
        return listProductBranchResponse;
    }

    @Override
    public Inventory addNewInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = mapper.map(inventoryRequest, Inventory.class);
        Inventory inventory1 = inventoryRepository.getFirstByProductIdAndBranchId(inventoryRequest.getProductId(), inventoryRequest.getBranchId());
        if (inventory1 != null) {
            inventory1.setQuantity(inventory1.getQuantity() + inventoryRequest.getQuantity());
        } else {
            inventory1 = inventory;
        }

        if (inventoryRequest.getIsWarehouse()) {
            Warehouse warehouse = warehouseRepository.findFirstByProductId(inventoryRequest.getProductId());
            if (warehouse != null) {
                warehouse.setQuantity(warehouse.getQuantity() - inventoryRequest.getQuantity());
                warehouseRepository.save(warehouse);
            }
        } else {
            ProductOfBranch product = productOfBranchRepository.getFirstByProductIdAndBranchId(inventoryRequest.getProductId(), inventoryRequest.getBranchId());
            if (product != null) {
                product.setQuantity(product.getQuantity() - inventoryRequest.getQuantity());
            }

        }
        return inventoryRepository.save(inventory1);

    }

    @Override
    public Boolean deleteInventoryById(DeleteProductInventoryRequest deleteProductInventoryRequest) {
        try {
            Optional<Inventory> inventoryOptional = inventoryRepository.findById(deleteProductInventoryRequest.getId());
            if (!inventoryOptional.isPresent()) {
                return false;
            }
            Inventory inventory = inventoryOptional.get();
            inventory.setQuantity(inventory.getQuantity() - deleteProductInventoryRequest.getQuantity());
            inventoryRepository.save(inventory);
            DeleteHistory deleteHistory = new DeleteHistory();
            deleteHistory.setInventoryId(inventory.getId());
            deleteHistory.setQuantity(deleteProductInventoryRequest.getQuantity());
            deleteHistory.setAccountId(deleteProductInventoryRequest.getAccountId());
            deleteHistory.setAccountName(deleteProductInventoryRequest.getAccountName());
            deleteHistory.setReason(deleteProductInventoryRequest.getReason());
            deleteHistory.setBranchId(deleteProductInventoryRequest.getBranchId());
            deleteHistory.setDeleteDate(new Date(System.currentTimeMillis()));
            deleteHistoryRepository.save(deleteHistory);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public List<DeleteHistory> viewDeleteHistory(Long id) {
        return deleteHistoryRepository.findAllByBranchId(id);
    }
}
