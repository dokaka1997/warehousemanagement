package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.entity.Role;
import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;
import com.example.warehousemanagement.model.response.AccountResponse;
import com.example.warehousemanagement.repository.AccountRepository;
import com.example.warehousemanagement.repository.RoleRepository;
import com.example.warehousemanagement.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    RoleRepository roleRepository;
    ModelMapper mapper;

    @Autowired

    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }


    @Override
    public AccountResponse login(LoginRequest loginRequest) {
        Account account = accountRepository.findAllByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        Optional<Role> optionalRole = roleRepository.findById((long) account.getRole());
        AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
        if (optionalRole.isPresent()) {
            accountResponse.setRoleId(optionalRole.get().getId());
            accountResponse.setRoleName(optionalRole.get().getName());
        }
        return accountResponse;
    }

    @Override
    public AccountResponse register(RegisterRequest registerRequest) {
        Account account = new Account();
        if (registerRequest.getId() != null) {
            account.setId(registerRequest.getId());
        }
        account.setEmail(registerRequest.getEmail());
        account.setFullName(registerRequest.getFullName());
        account.setPassword(registerRequest.getPassword());
        account.setUsername(registerRequest.getUsername());
        account.setRole(registerRequest.getRole());
        account.setImage(registerRequest.getImage());
        accountRepository.save(account);
        return mapper.map(account, AccountResponse.class);
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (!optionalAccount.isPresent()) {
            return null;
        }
        Account account = optionalAccount.get();
        return mapper.map(account, AccountResponse.class);

    }

    @Override
    public AccountResponse searchAccountByEmail(String email) {
        return mapper.map(accountRepository.findAllByEmailContaining(email), AccountResponse.class);
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

    @Override
    public List<AccountResponse> getAll(String username, String fullName, int pageIndex, int pageSize) {
        List<Account> accounts;
        List<AccountResponse> accountsResponse = new ArrayList<>();
        if (fullName.isEmpty() && username.isEmpty()) {
            accounts = accountRepository.findAll(PageRequest.of(pageIndex, pageSize)).getContent();
        } else {
            if (username.isEmpty()) {
                accounts = accountRepository.findAllByFullNameContaining(fullName);
            } else if (fullName.isEmpty()) {
                accounts = accountRepository.findAllByUsernameContaining(username);
            } else {
                accounts = accountRepository.findAllByUsernameContainingOrFullNameContaining(username, fullName);
            }
            int start = pageIndex * pageSize;
            for (int i = 0; i < accounts.size(); i++) {
                if (i >= start && i < start + pageSize) {
                    if (i > start + pageSize) {
                        break;
                    }
                    accounts.add(accounts.get(i));
                }
            }
        }
        for (Account account : accounts) {
            AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
            accountsResponse.add(accountResponse);
        }
        return accountsResponse;
    }
}
