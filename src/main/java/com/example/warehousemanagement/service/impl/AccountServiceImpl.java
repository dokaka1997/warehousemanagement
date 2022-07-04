package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;
import com.example.warehousemanagement.repository.AccountRepository;
import com.example.warehousemanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account login(LoginRequest loginRequest) {
        return accountRepository.findAllByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @Override
    public Account register(RegisterRequest registerRequest) {
        Account account = new Account();
        account.setEmail(registerRequest.getEmail());
        account.setFullName(registerRequest.getFullName());
        account.setPassword(registerRequest.getPassword());
        account.setUsername(registerRequest.getUsername());
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElse(null);

    }

    @Override
    public Account searchAccountByEmail(String email) {
        return accountRepository.findAllByEmailContaining(email);
    }

    @Override
    public Boolean deleteAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.deleteById(optionalAccount.get().getId());
            return true;
        }
        return false;
    }
}
