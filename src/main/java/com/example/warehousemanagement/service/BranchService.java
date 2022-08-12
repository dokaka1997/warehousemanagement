package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.model.response.GetAllBranchResponse;
import com.example.warehousemanagement.model.response.IncomeBranchResponse;

import java.util.List;

public interface BranchService {

    List<IncomeBranchResponse> getListIncomeBranch(String startDate, String endDate, Long branchId);

    Branch addNewBranch(Branch branch);

    GetAllBranchResponse getAllBranch(int pageIndex, int pageSize, String name, Boolean active);

    List<Account> getAllBranchManager();

    Branch getBranchById(Long id);

    Branch activeBranch(Long id, Boolean status);

    Boolean deleteBranch(Long id);
}
