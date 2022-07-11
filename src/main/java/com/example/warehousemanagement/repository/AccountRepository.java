package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAllByUsernameAndPassword(String username, String password);

    Account findAllByEmailContaining(String email);
}
