package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long branchId;

    private int quantity;

    private Long idCategory;

    private String name;

    private String image;

    private Double price;

    private int size;

    private String position;

}
