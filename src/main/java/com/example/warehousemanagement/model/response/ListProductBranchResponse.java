package com.example.warehousemanagement.model.response;

import com.example.warehousemanagement.entity.ProductOfBranch;
import lombok.Data;

import java.util.List;

@Data
public class ListProductBranchResponse {
    private int total;

    private List<ProductOfBranch> products;
}
