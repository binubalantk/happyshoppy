package com.binubalan.HappyShoppy.AuthService.controllers;

import com.binubalan.HappyShoppy.AuthService.abstractions.UserService;
import com.binubalan.HappyShoppy.AuthService.entities.AppUser;
import com.binubalan.HappyShoppy.AuthService.entities.Role;
import com.binubalan.HappyShoppy.AuthService.models.AddUserRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<Collection<AppUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/createUser")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user){
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user/createUser")
                .toUriString()
        );
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/createRole")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user/createRole")
                .toUriString()
        );
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/addRole")
    public ResponseEntity addRole(@RequestBody AddUserRoleRequest addUserRoleRequest){
        userService.addRoleToUser(
                addUserRoleRequest.getUsername(),
                addUserRoleRequest.getPassword());
        return ResponseEntity.ok().build();
    }


}
