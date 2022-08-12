package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "position_warehouse")
public class PositionWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long warehouseId;

    private boolean status;

    private String name;
}
