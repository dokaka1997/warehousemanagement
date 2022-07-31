package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "import_detail")
public class ImportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;

    private double totalPrice;

    private Long productId;
}
