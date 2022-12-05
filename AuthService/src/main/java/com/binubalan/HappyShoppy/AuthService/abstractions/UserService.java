package com.binubalan.HappyShoppy.AuthService.abstractions;

import com.binubalan.HappyShoppy.AuthService.entities.AppUser;
import com.binubalan.HappyShoppy.AuthService.entities.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();
}
