package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "detele_history")
public class DeleteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long inventoryId;

    private Long branchId;

    private int quantity;

    private Date deleteDate;

    private String reason;

    private Long accountId;

    private String accountName;
}
