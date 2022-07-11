package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.model.response.IncomeBranchResponse;
import com.example.warehousemanagement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("branch")
public class BranchController {

    BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/income/{id}")
    public ResponseEntity<IncomeBranchResponse> getIncomeBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getIncomeBranchById(id));
    }

    @GetMapping("/income/all")
    public ResponseEntity<List<IncomeBranchResponse>> getAllIncomeBranch() {
        return ResponseEntity.ok(branchService.getListIncomeBranch());
    }

}
