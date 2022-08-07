package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Inventory;
import com.example.warehousemanagement.model.response.ListProductBranchResponse;
import com.example.warehousemanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("inventory")
public class InventoryController {

    InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<ListProductBranchResponse> getListProductOfBranch(@RequestParam int pageIndex,
                                                                            @RequestParam int pageSize,
                                                                            @RequestParam(required = false, defaultValue = "") String name,
                                                                            @RequestParam(required = false, defaultValue = "-1") int size,
                                                                            @RequestParam(required = false, defaultValue = "-1") Long category,
                                                                            @RequestParam Long branchId) {

        return ResponseEntity.ok(inventoryService.getListProductOfBranch(pageIndex, pageSize, branchId, name, size, category));
    }

    @PostMapping
    public ResponseEntity<Inventory> addNewInventory(@RequestBody Inventory inventory) {

        return ResponseEntity.ok(inventoryService.addNewInventory(inventory));
    }

}
