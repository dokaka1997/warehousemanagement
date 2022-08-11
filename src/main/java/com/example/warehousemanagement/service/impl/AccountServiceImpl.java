package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.entity.Role;
import com.example.warehousemanagement.entity.Warehouse;
import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;
import com.example.warehousemanagement.model.response.AccountResponse;
import com.example.warehousemanagement.model.response.GetAllAccountResponse;
import com.example.warehousemanagement.model.response.ListBranchAndWarehouseResponse;
import com.example.warehousemanagement.repository.AccountRepository;
import com.example.warehousemanagement.repository.BranchRepository;
import com.example.warehousemanagement.repository.RoleRepository;
import com.example.warehousemanagement.repository.WarehouseRepository;
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
    BranchRepository branchRepository;
    WarehouseRepository warehouseRepository;

    @Autowired

    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, ModelMapper mapper,
                              BranchRepository branchRepository, WarehouseRepository warehouseRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.branchRepository = branchRepository;
        this.warehouseRepository = warehouseRepository;
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
        if (accountRepository.findAllByUsernameContainingOrEmailContaining(registerRequest.getUsername(), registerRequest.getEmail()) != null) {
            throw new RuntimeException("Username or Email existed !!!");
        }
        Account account = new Account();
        account.setEmail(registerRequest.getEmail());
        account.setFullName(registerRequest.getFullName());
        account.setPassword(registerRequest.getPassword());
        account.setUsername(registerRequest.getUsername());
        account.setRole(registerRequest.getRole());
        account.setImage(registerRequest.getImage());
        accountRepository.save(account);
        AccountResponse accountResponse;
        accountResponse = mapper.map(account, AccountResponse.class);
        Optional<Role> optionalRole = roleRepository.findById((long) account.getRole());
        if (optionalRole.isPresent()) {
            accountResponse.setRoleId(optionalRole.get().getId());
            accountResponse.setRoleName(optionalRole.get().getName());
        }
        return accountResponse;
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (!optionalAccount.isPresent()) {
            return null;
        }
        Account account = optionalAccount.get();
        AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
        Optional<Role> optionalRole = roleRepository.findById((long) account.getRole());
        if (optionalRole.isPresent()) {
            accountResponse.setRoleId(optionalRole.get().getId());
            accountResponse.setRoleName(optionalRole.get().getName());
        }
        return accountResponse;

    }

    @Override
    public AccountResponse searchAccountByEmail(String email) {
        Account account = accountRepository.findAllByEmailContaining(email);
        AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
        Optional<Role> optionalRole = roleRepository.findById((long) account.getRole());
        if (optionalRole.isPresent()) {
            accountResponse.setRoleId(optionalRole.get().getId());
            accountResponse.setRoleName(optionalRole.get().getName());
        }
        return accountResponse;
    }


    @Override
    public AccountResponse updateAccountResponse(RegisterRequest registerRequest) {
        if (registerRequest.getId() == null) {
            throw new RuntimeException("Id can't not be null");
        }
        Optional<Account> optionalAccount = accountRepository.findById(registerRequest.getId());
        if (!optionalAccount.isPresent()) {
            throw new RuntimeException("Account not existed.");
        }
        Account account = optionalAccount.get();
        account.setId(registerRequest.getId());
        account.setEmail(registerRequest.getEmail());
        account.setFullName(registerRequest.getFullName());
        account.setPassword(registerRequest.getPassword());
        account.setUsername(registerRequest.getUsername());
        account.setRole(registerRequest.getRole());
        account.setImage(registerRequest.getImage());
        accountRepository.save(account);
        AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
        Optional<Role> optionalRole = roleRepository.findById((long) account.getRole());
        if (optionalRole.isPresent()) {
            accountResponse.setRoleId(optionalRole.get().getId());
            accountResponse.setRoleName(optionalRole.get().getName());
        }
        return accountResponse;
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
    public GetAllAccountResponse getAll(String username, String fullName, String email, int role, int pageIndex, int pageSize) {
        GetAllAccountResponse getAllAccountResponse = new GetAllAccountResponse();
        List<Account> accounts;
        List<AccountResponse> accountsResponse = new ArrayList<>();
        if (role != -1) {
            accounts = accountRepository.findAllByUsernameContainingAndFullNameContainingAndEmailContainingAndRoleIs(username, fullName, email, role, PageRequest.of(pageIndex, pageSize));
        } else {
            accounts = accountRepository.findAllByUsernameContainingAndFullNameContainingAndEmailContaining(username, fullName, email, PageRequest.of(pageIndex, pageSize));
        }
        for (Account account : accounts) {
            AccountResponse accountResponse = mapper.map(account, AccountResponse.class);
            Optional<Role> optionalRole = roleRepository.findById((long) account.getRole());
            if (optionalRole.isPresent()) {
                accountResponse.setRoleId(optionalRole.get().getId());
                accountResponse.setRoleName(optionalRole.get().getName());
            }
            accountsResponse.add(accountResponse);
        }
        getAllAccountResponse.setTotal(accountRepository.findAll().size());
        getAllAccountResponse.setAccounts(accountsResponse);
        return getAllAccountResponse;
    }

    @Override
    public ListBranchAndWarehouseResponse getBranchAndWarehouseById(Long id) {
        ListBranchAndWarehouseResponse listBranchAndWarehouseResponse = new ListBranchAndWarehouseResponse();
        List<Warehouse> warehouses = warehouseRepository.findAll();

        List<Branch> branches = branchRepository.findAll();

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (!optionalAccount.isPresent()) {
            throw new RuntimeException("Account not existed !!!");
        }
        Account account = optionalAccount.get();

        List<Warehouse> finalWarehouses = new ArrayList<>();

        List<Branch> finalBranches = new ArrayList<>();

        for (Warehouse warehouse : warehouses) {
            if (warehouse.getId().equals(account.getWarehouseId())) {
                finalWarehouses.add(warehouse);
            }
        }

        for (Branch branch : branches) {
            if (branch.getId().equals(account.getWarehouseId())) {
                finalBranches.add(branch);
            }
        }
        listBranchAndWarehouseResponse.setWarehouses(finalWarehouses);
        listBranchAndWarehouseResponse.setBranches(finalBranches);

        return listBranchAndWarehouseResponse;
    }
}
