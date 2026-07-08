package com.example.fleetfocus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleetfocus.entity.SystemUser;
import com.example.fleetfocus.enums.Role;

public interface SystemUserRepo extends JpaRepository<SystemUser,Long> {
    Optional<SystemUser> findByUsername(String username);
    List<SystemUser> findByRole(Role role);
}
