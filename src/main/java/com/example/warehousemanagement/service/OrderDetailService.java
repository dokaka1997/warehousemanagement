package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.response.BestProductSellingResponse;

import java.util.List;

public interface OrderDetailService {
    List<BestProductSellingResponse> getTop10BestSellingProduct();

    Double getTotalOrder();
}
