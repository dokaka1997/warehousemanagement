package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product_of_branch")
public class ProductOfBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long branchId;

    private Long productId;

    private int quantity;

    private String position;

}
