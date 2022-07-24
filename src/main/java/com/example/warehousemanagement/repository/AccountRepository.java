package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAllByUsernameAndPassword(String username, String password);

    Account findAllByEmailContaining(String email);

    List<Account> findAllByUsernameContainingOrFullNameContaining(String username, String fullName);

    List<Account> findAllByUsernameContaining(String username);

    List<Account> findAllByFullNameContaining(String fullName);
}
