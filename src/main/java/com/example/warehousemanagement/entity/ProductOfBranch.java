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

    private Long position;

    private Long idCategory;

    private String name;

    private String image;

    private Double price;

    private int size;

    private String sku;


}
