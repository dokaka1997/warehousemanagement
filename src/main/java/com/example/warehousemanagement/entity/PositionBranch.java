package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "position_branch")
public class PositionBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long branchId;

    private boolean status;

    private String name;
}
