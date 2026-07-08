package com.example.fleetfocus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fleetfocus.dto.AuthResponse;
import com.example.fleetfocus.dto.LoginRequest;
import com.example.fleetfocus.dto.RegisterRequest;
import com.example.fleetfocus.entity.SystemUser;
import com.example.fleetfocus.enums.Role;
import com.example.fleetfocus.exception.InvalidCredentialsException;
import com.example.fleetfocus.exception.UserExistException;
import com.example.fleetfocus.exception.UserNotFoundException;
import com.example.fleetfocus.repository.SystemUserRepo;
import com.example.fleetfocus.security.JwtService;
@Service
public class AuthServiceImpl implements AuthService{
    private final SystemUserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthServiceImpl(SystemUserRepo repo, PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }
    @Override
    public AuthResponse register(RegisterRequest request){
    Optional<SystemUser> existingUser = repo.findByUsername(request.getUsername());
    if(existingUser.isPresent()){
        throw new UserExistException("Username already exist");
    }
    SystemUser user=new SystemUser();
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setEmail(request.getEmail());
    user.setRole(request.getRole());
    repo.save(user);
    return  new AuthResponse("User Registered Successfully");    
    }
    @Override
    public AuthResponse login(LoginRequest request){
        
        SystemUser user=repo.findByUsername(request.getUsername()).orElseThrow(()->new UserNotFoundException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("Invalid username or password");
        }
        String token = jwtService.generateToken(user.getUsername());
        System.out.println("Generated token : "+token);
        return new AuthResponse("Login successfull", token, user.getRole());
    }
    @Override
    public List<SystemUser> getDriverUsers() {
        return repo.findByRole(Role.DRIVER);
}
}
