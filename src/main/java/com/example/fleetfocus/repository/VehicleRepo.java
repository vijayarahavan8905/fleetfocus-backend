package com.example.fleetfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fleetfocus.entity.Vehicle;
import com.example.fleetfocus.enums.VehicleStatus;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle,Long>{
    List<Vehicle> findByStatus(VehicleStatus status);

    boolean existsByVin(String vin);

    boolean existsByLicensePlate(String licensePlate);
    
}
