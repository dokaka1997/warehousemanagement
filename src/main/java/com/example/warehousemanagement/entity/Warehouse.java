package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    private int quantity;
}
