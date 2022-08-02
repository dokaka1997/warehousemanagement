package com.example.warehousemanagement.model.response;

import com.example.warehousemanagement.entity.Branch;
import lombok.Data;

import java.util.List;

@Data
public class GetAllBranchResponse {
    private int total;

    private List<Branch> branches;
}
