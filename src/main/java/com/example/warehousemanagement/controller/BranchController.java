package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.model.response.GetAllBranchResponse;
import com.example.warehousemanagement.model.response.IncomeBranchResponse;
import com.example.warehousemanagement.model.response.ListProductBranchResponse;
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

    @GetMapping("/income")
    public ResponseEntity<List<IncomeBranchResponse>> getAllIncomeBranch(@RequestParam String startDate,
                                                                         @RequestParam String endDate,
                                                                         @RequestParam(required = false, defaultValue = "-1") Long branchId) {
        return ResponseEntity.ok(branchService.getListIncomeBranch(startDate, endDate, branchId));
    }

    @PostMapping
    public ResponseEntity<Branch> addNewBranch(@RequestBody Branch branch) {
        return ResponseEntity.ok(branchService.addNewBranch(branch));
    }

    @GetMapping()
    public ResponseEntity<GetAllBranchResponse> getAllBranch(@RequestParam int pageIndex,
                                                             @RequestParam int pageSize,
                                                             @RequestParam(required = false, defaultValue = "") String name,
                                                             @RequestParam(required = false) Boolean active) {
        return ResponseEntity.ok(branchService.getAllBranch(pageIndex, pageSize, name, active));
    }

    @GetMapping("manager")
    public ResponseEntity<List<Account>> getAllBranchManager() {
        return ResponseEntity.ok(branchService.getAllBranchManager());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @GetMapping("active")
    public ResponseEntity<Branch> activeBranch(@RequestParam Long id,
                                               @RequestParam(required = false, defaultValue = "true") boolean active) {
        return ResponseEntity.ok(branchService.activeBranch(id, active));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.deleteBranch(id));
    }

    @GetMapping("/product")
    public ResponseEntity<ListProductBranchResponse> getListProductOfBranch(@RequestParam int pageIndex,
                                                                            @RequestParam int pageSize,
                                                                            @RequestParam(required = false, defaultValue = "") String name,
                                                                            @RequestParam(required = false, defaultValue = "-1") int size,
                                                                            @RequestParam(required = false, defaultValue = "-1") Long category,
                                                                            @RequestParam Long branchId) {

        return ResponseEntity.ok(branchService.getListProductOfBranch(pageIndex, pageSize, name, size, category, branchId));
    }

}
