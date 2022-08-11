package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAllByUsernameAndPassword(String username, String password);

    Account findAllByEmailContaining(String email);

    Account findAllByUsernameContainingOrEmailContaining(String username, String email);

    List<Account> findAllByUsernameContainingAndFullNameContainingAndEmailContaining(String username, String fullName, String email, Pageable pageable);

    List<Account> findAllByUsernameContainingAndFullNameContainingAndEmailContainingAndRoleIs(String username, String fullName, String email, int role, Pageable pageable);

    List<Account> findAllByUsernameContaining(String username);

    List<Account> findAllByFullNameContaining(String fullName);

    List<Account> findAllByRole(int role);

    List<Account> findAllByRoleAndIdBranchNull(int role);
}
