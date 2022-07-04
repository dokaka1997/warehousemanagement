package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.model.request.LoginRequest;
import com.example.warehousemanagement.model.request.RegisterRequest;
import com.example.warehousemanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Account> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(accountService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(accountService.register(registerRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Account> searchAccountByEmail(@PathVariable String email) {
        return ResponseEntity.ok(accountService.searchAccountByEmail(email));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAcountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.deleteAccountById(id));
    }

}
