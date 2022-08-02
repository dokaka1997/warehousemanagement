package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.constant.RoleUser;
import com.example.warehousemanagement.entity.Account;
import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.entity.Order;
import com.example.warehousemanagement.entity.Role;
import com.example.warehousemanagement.model.response.AccountResponse;
import com.example.warehousemanagement.model.response.GetAllBranchResponse;
import com.example.warehousemanagement.model.response.IncomeBranchResponse;
import com.example.warehousemanagement.repository.AccountRepository;
import com.example.warehousemanagement.repository.BranchRepository;
import com.example.warehousemanagement.repository.OrderRepository;
import com.example.warehousemanagement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository;
    OrderRepository orderRepository;
    AccountRepository accountRepository;


    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, OrderRepository orderRepository, AccountRepository accountRepository) {
        this.branchRepository = branchRepository;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public IncomeBranchResponse getIncomeBranchById(Long id) {
        IncomeBranchResponse incomeBranchResponse = new IncomeBranchResponse();
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {
            incomeBranchResponse.setId(id);
            incomeBranchResponse.setBranchName(optionalBranch.get().getName());

        }
        List<Order> orders = orderRepository.getAllByBranchId(id);
        Double price = 0D;
        for (Order order : orders) {
            price += order.getTotalPrice();
        }
        incomeBranchResponse.setIncome(price);
        return incomeBranchResponse;
    }

    @Override
    public List<IncomeBranchResponse> getListIncomeBranch(String date) {
        List<IncomeBranchResponse> incomeBranchResponses = new ArrayList<>();
        List<Branch> branches = branchRepository.findAll();
        for (Branch branch : branches) {
            IncomeBranchResponse incomeBranchResponse = new IncomeBranchResponse();
            Optional<Branch> optionalBranch = branchRepository.findById(branch.getId());

            if (optionalBranch.isPresent()) {
                incomeBranchResponse.setId(branch.getId());
                incomeBranchResponse.setBranchName(optionalBranch.get().getName());
            }
            List<Order> orders = orderRepository.getAllByBranchId(branch.getId());
            Double price = 0D;
            for (Order order : orders) {
                price += order.getTotalPrice();
            }
            incomeBranchResponse.setIncome(price);
            incomeBranchResponses.add(incomeBranchResponse);
        }

        return incomeBranchResponses;
    }

    @Override
    public Branch addNewBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public GetAllBranchResponse getAllBranch(int pageIndex, int pageSize, String name, boolean active) {
        GetAllBranchResponse response = new GetAllBranchResponse();
        List<Branch> branches = branchRepository.findAllByNameContainingAndActiveIs(name, active, PageRequest.of(pageIndex, pageSize));
        response.setTotal(branchRepository.findAll().size());
        response.setBranches(branches);
        return response;
    }

    @Override
    public List<Account> getAllBranchManager() {
        List<Account> accounts = accountRepository.findAllByRole(RoleUser.BRANCH_MANAGER);
        List<Branch> branches = branchRepository.findAll();
        for (Branch branch : branches) {
            accounts.removeIf(account -> Objects.equals(account.getId(), branch.getAccountId()));
        }
        return accounts;
    }
}
