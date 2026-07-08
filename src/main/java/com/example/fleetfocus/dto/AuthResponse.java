package com.example.fleetfocus.dto;

import com.example.fleetfocus.enums.Role;

public class AuthResponse {
    private String message;
    private String token;
    private Role role;

    public AuthResponse(){

    }

    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public AuthResponse(String message, String token, Role role) {
        this.message = message;
        this.token = token;
        this.role = role;
    }

    public AuthResponse(String msg){
        this.message=msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
