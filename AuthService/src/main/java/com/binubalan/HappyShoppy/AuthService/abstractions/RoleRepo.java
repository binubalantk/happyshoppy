package com.binubalan.HappyShoppy.AuthService.abstractions;

import com.binubalan.HappyShoppy.AuthService.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
