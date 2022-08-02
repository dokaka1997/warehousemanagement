package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.model.response.AccountResponse;
import com.example.warehousemanagement.model.response.GetAllBranchResponse;
import com.example.warehousemanagement.model.response.IncomeBranchResponse;

import java.util.List;

public interface BranchService {
    IncomeBranchResponse getIncomeBranchById(Long id);

    List<IncomeBranchResponse> getListIncomeBranch(String date);

    Branch addNewBranch(Branch branch);

    GetAllBranchResponse getAllBranch(int pageIndex, int pageSize, String name, boolean active);

    List<Account> getAllBranchManager();

}
