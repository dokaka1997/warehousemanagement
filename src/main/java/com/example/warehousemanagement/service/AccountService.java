package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;
import com.example.warehousemanagement.model.response.AccountResponse;
import com.example.warehousemanagement.model.response.GetAllAccountResponse;
import com.example.warehousemanagement.model.response.ListBranchAndWarehouseResponse;

public interface AccountService {
    AccountResponse login(LoginRequest loginRequest);

    AccountResponse register(RegisterRequest registerRequest);

    AccountResponse getAccountById(Long id);

    AccountResponse searchAccountByEmail(String email);

    AccountResponse updateAccountResponse(RegisterRequest registerRequest);

    Boolean deleteAccountById(Long id);

    GetAllAccountResponse getAll(String username, String fullName, String email, int role,
                                 int pageIndex, int pageSize);

    ListBranchAndWarehouseResponse getBranchAndWarehouseById(Long id);
}
