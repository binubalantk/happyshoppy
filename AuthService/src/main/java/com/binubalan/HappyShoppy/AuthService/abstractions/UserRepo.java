package com.binubalan.HappyShoppy.AuthService.abstractions;

import com.binubalan.HappyShoppy.AuthService.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<AppUser, UUID> {
    AppUser findByUsername(String username);
}
