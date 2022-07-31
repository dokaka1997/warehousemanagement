package com.example.warehousemanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "export")
public class Export {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date exportDate;

    private Long employee;

}
