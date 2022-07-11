package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.response.IncomeBranchResponse;

import java.util.List;

public interface BranchService {
    IncomeBranchResponse getIncomeBranchById(Long id);

    List<IncomeBranchResponse> getListIncomeBranch();

}
