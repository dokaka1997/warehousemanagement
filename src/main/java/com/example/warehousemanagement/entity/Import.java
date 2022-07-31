package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "import")
public class Import {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date importDate;

    private double totalPrice;

    private Long employeeId;


}
