package com.example.warehousemanagement.service;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;

public interface AccountService {
    Account login (LoginRequest loginRequest);

    Account register (RegisterRequest registerRequest);

    Account getAccountById(Long id);

    Account searchAccountByEmail(String email);

    Boolean deleteAccountById(Long id);
}
