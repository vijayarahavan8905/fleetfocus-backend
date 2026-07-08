package com.example.fleetfocus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleetfocus.dto.AuthResponse;
import com.example.fleetfocus.dto.LoginRequest;
import com.example.fleetfocus.dto.RegisterRequest;
import com.example.fleetfocus.entity.SystemUser;
import com.example.fleetfocus.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    public AuthController(AuthService authService) {
        this.service = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = service.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/driverUsers")
    @PreAuthorize("hasRole('FLEET_MANAGER')")
    public ResponseEntity<List<SystemUser>> getDriverUsers() {
    return ResponseEntity.ok(service.getDriverUsers());
    }
}
