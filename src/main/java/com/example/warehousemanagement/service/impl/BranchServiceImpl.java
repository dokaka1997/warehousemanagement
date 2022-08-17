package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.constant.RoleUser;
import com.example.warehousemanagement.entity.*;
import com.example.warehousemanagement.model.response.GetAllBranchResponse;
import com.example.warehousemanagement.model.response.IncomeBranchResponse;
import com.example.warehousemanagement.model.response.ListProductBranchResponse;
import com.example.warehousemanagement.model.response.ListProductInventoryResponse;
import com.example.warehousemanagement.repository.AccountRepository;
import com.example.warehousemanagement.repository.BranchRepository;
import com.example.warehousemanagement.repository.OrderRepository;
import com.example.warehousemanagement.repository.ProductOfBranchRepository;
import com.example.warehousemanagement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository;
    OrderRepository orderRepository;
    AccountRepository accountRepository;
    ProductOfBranchRepository productOfBranchRepository;


    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, OrderRepository orderRepository,
                             AccountRepository accountRepository, ProductOfBranchRepository productOfBranchRepository) {
        this.branchRepository = branchRepository;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
        this.productOfBranchRepository = productOfBranchRepository;
    }


    @Override
    public List<IncomeBranchResponse> getListIncomeBranch(String startDate, String endDate, Long branchId) {
        List<IncomeBranchResponse> incomeBranchResponses = new ArrayList<>();
        Optional<Branch> optionalBranch = branchRepository.findById(branchId);

        Date start = subtractDays(Date.valueOf(startDate), 1);
        Date end = addDays(Date.valueOf(endDate), 1);

        if (!optionalBranch.isPresent()) {
            throw new RuntimeException("Branch Not Existed!!!!");
        }
        Branch branch = optionalBranch.get();
        List<Order> orders = orderRepository.getAllByBranchId(branch.getId());
        Map<Date, Double> income = new HashMap<>();
        for (Order order : orders) {
            if (order.getOrderDate().after(start) && order.getOrderDate().before(end)) {
                if (income.containsKey(order.getOrderDate())) {
                    Double total = order.getTotalPrice() + income.get(order.getOrderDate());
                    income.put(order.getOrderDate(), total);
                } else {
                    income.put(order.getOrderDate(), order.getTotalPrice());
                }
            }
        }
        for (Map.Entry<Date, Double> entry : income.entrySet()) {
            IncomeBranchResponse incomeBranchResponse = new IncomeBranchResponse();
            incomeBranchResponse.setDate(entry.getKey());
            incomeBranchResponse.setIncome(entry.getValue());
            incomeBranchResponses.add(incomeBranchResponse);
        }
        Collections.sort(incomeBranchResponses);
        return incomeBranchResponses;
    }

    @Override
    public Branch addNewBranch(Branch branch) {
        Optional<Account> optionalAccount = accountRepository.findById(branch.getAccountId());
        if (!optionalAccount.isPresent()) {
            throw new RuntimeException("Account not found");
        }
        Account account = optionalAccount.get();
        branch = branchRepository.save(branch);
        account.setIdBranch(branch.getId());
        accountRepository.save(account);
        return branch;
    }

    @Override
    public GetAllBranchResponse getAllBranch(int pageIndex, int pageSize, String name, Boolean active) {
        GetAllBranchResponse response = new GetAllBranchResponse();
        List<Branch> branches;
        if (active == null) {
            branches = branchRepository.findAllByNameContaining(name, PageRequest.of(pageIndex, pageSize));
        } else {
            branches = branchRepository.findAllByNameContainingAndActiveIs(name, active, PageRequest.of(pageIndex, pageSize));
        }
        List<Branch> rs = new ArrayList<>();
        for (Branch branch : branches) {
            Optional<Account> optionalAccount = accountRepository.findById(branch.getAccountId());
            if (optionalAccount.isPresent()) {
                branch.setAccountName(optionalAccount.get().getUsername());
            }
            rs.add(branch);
        }
        if (active != null) {
            response.setTotal(branchRepository.findAllByNameContainingAndActiveIs(name, active).size());
        } else {
            response.setTotal(branchRepository.findAllByNameContaining(name).size());
        }
        response.setBranches(rs);
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

    @Override
    public Branch getBranchById(Long id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        return optionalBranch.orElse(null);
    }

    @Override
    public Branch activeBranch(Long id, Boolean status) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if (!optionalBranch.isPresent()) {
            return null;
        }
        Branch branch = optionalBranch.get();
        branch.setActive(status);
        return branchRepository.save(branch);
    }

    @Override
    public Boolean deleteBranch(Long id) {
        try {
            branchRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public static Date subtractDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new Date(c.getTimeInMillis());
    }

    @Override
    public ListProductBranchResponse getListProductOfBranch(int pageIndex, int pageSize, String name, int size, Long category, Long branchId) {
        ListProductBranchResponse listProductBranchResponse = new ListProductBranchResponse();
        List<ProductOfBranch> product;
        if (size == -1 && category == -1) {
            product = productOfBranchRepository.findAllByBranchIdAndNameContaining(branchId, name, PageRequest.of(pageIndex, pageSize));
        } else {

            if (size == -1) {
                product = productOfBranchRepository.findAllByBranchIdAndNameContainingAndIdCategory(branchId, name, category, PageRequest.of(pageIndex, pageSize));
            } else if (category == -1) {
                product = productOfBranchRepository.findAllByBranchIdAndNameContainingAndSize(branchId, name, size, PageRequest.of(pageIndex, pageSize));
            } else {
                product = productOfBranchRepository.findAllByBranchIdAndNameContainingAndIdCategoryAndSize(branchId, name, category, size, PageRequest.of(pageIndex, pageSize));
            }
        }
        listProductBranchResponse.setProducts(product);
        listProductBranchResponse.setTotal(product.size());
        return listProductBranchResponse;
    }
}
