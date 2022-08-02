package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.response.ProductWarehouseResponse;
import com.example.warehousemanagement.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<ProductWarehouseResponse>> getAccountById() {
        return ResponseEntity.ok(warehouseService.getAllProduct());
    }
}
