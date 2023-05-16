package com.binubalan.HappyShoppy.AuthService.services;

import com.binubalan.HappyShoppy.AuthService.abstractions.RoleRepo;
import com.binubalan.HappyShoppy.AuthService.abstractions.UserRepo;
import com.binubalan.HappyShoppy.AuthService.abstractions.UserService;
import com.binubalan.HappyShoppy.AuthService.entities.AppUser;
import com.binubalan.HappyShoppy.AuthService.entities.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving the user {} to db", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        AppUser userSaved =  userRepo.save(user);
        userRepo.flush();
        return userSaved;
    }
    @Override
    public Role saveRole(Role role) {
        log.info("Saving the role {} to db", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding the role {} to the user {}", roleName, username);
        AppUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Getting user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Getting all users");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User {} not found", username);
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User {} found", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles()
                .forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                });

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
