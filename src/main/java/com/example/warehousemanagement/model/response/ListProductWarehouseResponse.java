package com.example.warehousemanagement.model.response;

import java.util.List;

public class ListProductWarehouseResponse {
    private int total;

    private List<ProductWarehouseResponse> products;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductWarehouseResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWarehouseResponse> products) {
        this.products = products;
    }
}
