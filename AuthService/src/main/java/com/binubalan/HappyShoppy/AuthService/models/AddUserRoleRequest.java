package com.binubalan.HappyShoppy.AuthService.models;

import lombok.Data;

@Data
public class AddUserRoleRequest {
    private String username;
    private String rolename;
}
