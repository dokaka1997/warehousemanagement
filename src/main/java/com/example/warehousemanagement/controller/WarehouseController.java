package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.response.ListProductWarehouseResponse;
import com.example.warehousemanagement.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("warehouse")
public class WarehouseController {

    WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<ListProductWarehouseResponse> getAccountById(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(warehouseService.getAllProduct(pageIndex, pageSize));
    }
}
