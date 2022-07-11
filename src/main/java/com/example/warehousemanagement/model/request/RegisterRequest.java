package com.example.warehousemanagement.model.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
}
