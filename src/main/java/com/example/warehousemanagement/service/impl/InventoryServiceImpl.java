package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.DeleteHistory;
import com.example.warehousemanagement.entity.Inventory;
import com.example.warehousemanagement.entity.PositionBranch;
import com.example.warehousemanagement.model.request.DeleteProductInventoryRequest;
import com.example.warehousemanagement.model.response.ListProductBranchResponse;
import com.example.warehousemanagement.repository.DeleteHistoryRepository;
import com.example.warehousemanagement.repository.InventoryRepository;
import com.example.warehousemanagement.repository.PositionBranchRepository;
import com.example.warehousemanagement.service.InventoryService;
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

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, DeleteHistoryRepository deleteHistoryRepository
            , PositionBranchRepository positionBranchRepository) {
        this.inventoryRepository = inventoryRepository;
        this.deleteHistoryRepository = deleteHistoryRepository;
        this.positionBranchRepository = positionBranchRepository;
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
        Optional<PositionBranch> optionalPositionBranch = positionBranchRepository.findById(inventory.getPosition());
        if (optionalPositionBranch.isPresent()) {
            PositionBranch positionBranch = optionalPositionBranch.get();
            positionBranch.setStatus(false);
            positionBranchRepository.save(positionBranch);
        }
        return inventoryRepository.save(inventory);
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
