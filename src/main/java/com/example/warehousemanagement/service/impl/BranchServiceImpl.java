package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Branch;
import com.example.warehousemanagement.entity.Order;
import com.example.warehousemanagement.model.response.IncomeBranchResponse;
import com.example.warehousemanagement.repository.BranchRepository;
import com.example.warehousemanagement.repository.OrderRepository;
import com.example.warehousemanagement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository;
    OrderRepository orderRepository;


    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, OrderRepository orderRepository) {
        this.branchRepository = branchRepository;
        this.orderRepository = orderRepository;
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
    public List<IncomeBranchResponse> getListIncomeBranch() {
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
}
