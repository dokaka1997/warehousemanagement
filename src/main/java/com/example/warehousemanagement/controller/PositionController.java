package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.DeleteHistory;
import com.example.warehousemanagement.entity.PositionBranch;
import com.example.warehousemanagement.entity.PositionWarehouse;
import com.example.warehousemanagement.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("position")
public class PositionController {

    PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<PositionBranch>> getAllPositionBranchById(@PathVariable Long id) {

        return ResponseEntity.ok(positionService.getPositionBranchById(id));
    }

    @GetMapping("/warehouse/{id}")
    public ResponseEntity<List<PositionWarehouse>> getAllPositionWarehouseById(@PathVariable Long id) {

        return ResponseEntity.ok(positionService.getPositionWarehouseById(id));
    }

}
