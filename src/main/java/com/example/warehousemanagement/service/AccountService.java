package com.example.warehousemanagement.service;

import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;
import com.example.warehousemanagement.model.response.AccountResponse;

import java.util.List;

public interface AccountService {
    AccountResponse login(LoginRequest loginRequest);

    AccountResponse register(RegisterRequest registerRequest);

    AccountResponse getAccountById(Long id);

    AccountResponse searchAccountByEmail(String email);

    Boolean deleteAccountById(Long id);

    List<AccountResponse> getAll(String username, String fullName,
                         int pageIndex, int pageSize);
}
