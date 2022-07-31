package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.model.response.IncomeBranchResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BranchService {
    IncomeBranchResponse getIncomeBranchById(Long id);

    List<IncomeBranchResponse> getListIncomeBranch();

    Branch addNewBranch(Branch branch);

    List<Branch> getAllBranch(int pageIndex, int pageSize, String name, boolean active);

}
