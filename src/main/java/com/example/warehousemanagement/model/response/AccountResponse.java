package com.example.warehousemanagement.model.response;

import lombok.Data;

@Data
public class AccountResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String roleName;
    private Long roleId;
    private String fullName;
    private Long idBranch;
    private String image;
}
