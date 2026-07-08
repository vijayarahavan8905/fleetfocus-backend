package com.example.fleetfocus.service;

import java.util.List;

import com.example.fleetfocus.dto.AuthResponse;
import com.example.fleetfocus.dto.LoginRequest;
import com.example.fleetfocus.dto.RegisterRequest;
import com.example.fleetfocus.entity.SystemUser;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    List<SystemUser> getDriverUsers();
}
