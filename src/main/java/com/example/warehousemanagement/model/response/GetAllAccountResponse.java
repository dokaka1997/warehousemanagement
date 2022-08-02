package com.example.warehousemanagement.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAllAccountResponse {
    private int total;

    private List<AccountResponse> accounts;
}
