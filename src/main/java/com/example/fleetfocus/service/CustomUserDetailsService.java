package com.example.fleetfocus.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.fleetfocus.entity.SystemUser;
import com.example.fleetfocus.exception.UserNotFoundException;
import com.example.fleetfocus.repository.SystemUserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SystemUserRepo repo;

    public CustomUserDetailsService(SystemUserRepo repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException{
        SystemUser user=repo.findByUsername(username).orElseThrow(()->new UserNotFoundException("user not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
