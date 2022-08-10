package com.example.warehousemanagement.model.response;

import java.util.List;

public class ListProductWarehouseResponse {
    private int total;

    private List<WarehouseResponse> products;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<WarehouseResponse> getProducts() {
        return products;
    }

    public void setProducts(List<WarehouseResponse> products) {
        this.products = products;
    }
}
