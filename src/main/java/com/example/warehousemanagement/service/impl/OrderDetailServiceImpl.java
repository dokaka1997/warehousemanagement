package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Order;
import com.example.warehousemanagement.entity.OrderDetail;
import com.example.warehousemanagement.entity.Product;
import com.example.warehousemanagement.model.response.BestProductSellingResponse;
import com.example.warehousemanagement.repository.OrderDetailRepository;
import com.example.warehousemanagement.repository.OrderRepository;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailRepository orderDetailRepository;

    OrderRepository orderRepository;

    ProductRepository productRepository;

    ModelMapper mapper;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository,
                                  ProductRepository productRepository, ModelMapper mapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<BestProductSellingResponse> getTop10BestSellingProduct() {
        List<BestProductSellingResponse> bestProductSellingResponses = new ArrayList<>();
        List<OrderDetail> orderDetails = orderDetailRepository.getTop10BestSellingProduct();
        for (OrderDetail orderDetail : orderDetails) {
            Optional<Product> product = productRepository.findById(orderDetail.getProductId());

            if (product.isPresent()) {
                BestProductSellingResponse bestProductSellingResponse = mapper.map(product.get(), BestProductSellingResponse.class);
                bestProductSellingResponse.setQuantity(orderDetail.getQuantity());
                bestProductSellingResponses.add(bestProductSellingResponse);
            }
        }

        return bestProductSellingResponses;
    }

    @Override
    public Double getTotalOrder() {
        List<Order> orders = orderRepository.findAll();
        Double total = 0D;
        for (Order order: orders){
            total += order.getTotalPrice();
        }
        return total;
    }
}
