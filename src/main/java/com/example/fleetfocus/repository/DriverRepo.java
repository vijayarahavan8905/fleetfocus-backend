package com.example.fleetfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleetfocus.entity.Driver;

public interface DriverRepo extends JpaRepository<Driver,Long> {

    boolean existsByLicenseNumber(String licenseNumber);
    
}
