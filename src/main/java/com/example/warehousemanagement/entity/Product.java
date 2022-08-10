package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idBranch;

    private Long idCategory;

    private Long warehouseId;

    private String name;

    private String image;

    private Double price;

    private int size;

    private String sku;

    private String position;

}
