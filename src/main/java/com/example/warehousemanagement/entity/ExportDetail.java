package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "export_detail")
public class ExportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long exportId;

    private int quantity;

    private Long productId;

    private double totalPrice;

    private Long branchId;
}
