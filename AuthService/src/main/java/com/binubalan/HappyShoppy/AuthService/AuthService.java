package com.binubalan.HappyShoppy.AuthService;

import com.binubalan.HappyShoppy.AuthService.abstractions.UserService;
import com.binubalan.HappyShoppy.AuthService.entities.AppUser;
import com.binubalan.HappyShoppy.AuthService.entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@Slf4j
public class AuthService {
    public static void main(String[] args) {
        log.info("Starting Auth spring service");
        SpringApplication.run(AuthService.class);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
          userService.saveRole(new Role(null, "Admin"));
          userService.saveRole(new Role(null, "Customer"));
          userService.saveRole(new Role(null, "Partner"));

          userService.saveUser(new AppUser(null, "Binu Balan", "binu@offical.com", "start123$", new ArrayList<>()));
          userService.saveUser(new AppUser(null, "Advika Binu", "advika@offical.com", "start123$", new ArrayList<>()));
          userService.saveUser(new AppUser(null, "Remisha Binu", "remisha@offical.com", "start123$", new ArrayList<>()));

          userService.addRoleToUser("advika@offical.com", "Admin");
          userService.addRoleToUser("binu@offical.com", "Customer");
          userService.addRoleToUser("remisha@offical.com", "Admin");
          userService.addRoleToUser("remisha@offical.com", "Partner");
        };
    }
}
