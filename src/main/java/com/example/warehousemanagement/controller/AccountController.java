package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;
import com.example.warehousemanagement.model.response.AccountResponse;
import com.example.warehousemanagement.model.response.GetAllAccountResponse;
import com.example.warehousemanagement.model.response.ListBranchAndWarehouseResponse;
import com.example.warehousemanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<AccountResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(accountService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AccountResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(accountService.register(registerRequest));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AccountResponse> searchAccountByEmail(@PathVariable String email) {
        return ResponseEntity.ok(accountService.searchAccountByEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.deleteAccountById(id));
    }

    @GetMapping()
    public ResponseEntity<GetAllAccountResponse> getAllAccount(@RequestParam(required = false, defaultValue = "") String username, @RequestParam(required = false, defaultValue = "") String fullName,
                                                               @RequestParam(required = false, defaultValue = "") String email, @RequestParam(required = false, defaultValue = "-1") int role,
                                                               @RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(accountService.getAll(username, fullName, email, role, pageIndex, pageSize));
    }


    @PutMapping("/update")
    public ResponseEntity<AccountResponse> update(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(accountService.updateAccountResponse(registerRequest));
    }

    @GetMapping("/branch_warehouse/{id}")
    public ResponseEntity<ListBranchAndWarehouseResponse> getBranchAndWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getBranchAndWarehouseById(id));
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<List<Account>> getAllStaffByBranchId(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAllStaffByBranchId(id));
    }


}
