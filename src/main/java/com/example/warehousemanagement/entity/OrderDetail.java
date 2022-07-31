package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;
    private double totalPrice;
    private Long orderId;
    private Long productId;
}
