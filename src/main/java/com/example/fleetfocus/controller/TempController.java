package com.example.fleetfocus.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TempController {
    @GetMapping("/auth")
    public String auth(Authentication authentication) {
        return authentication.getAuthorities().toString();
    }
}
