package com.example.warehousemanagement.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private int role;
    private String fullName;
    private Long idBranch;
    private Long warehouseId;
    private String image;
}
