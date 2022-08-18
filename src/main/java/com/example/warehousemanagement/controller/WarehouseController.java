package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Warehouse;
import com.example.warehousemanagement.model.request.AddWarehouseRequest;
import com.example.warehousemanagement.model.response.ImportWarehouseResponse;
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

    @PostMapping
    public ResponseEntity<ImportWarehouseResponse> addNewProduct(@RequestBody AddWarehouseRequest warehouse) {
        return ResponseEntity.ok(warehouseService.addNewWarehouse(warehouse));
    }

    @GetMapping
    public ResponseEntity<ListProductWarehouseResponse> getListProductOfWarehouse(@RequestParam int pageIndex,
                                                                                  @RequestParam int pageSize,
                                                                                  @RequestParam(required = false, defaultValue = "") String name,
                                                                                  @RequestParam(required = false, defaultValue = "-1") int size,
                                                                                  @RequestParam(required = false, defaultValue = "-1") Long category,
                                                                                  @RequestParam Long warehouseId) {

        return ResponseEntity.ok(warehouseService.getListProductOfWarehouse(pageIndex, pageSize, warehouseId, name, size, category));
    }


}
