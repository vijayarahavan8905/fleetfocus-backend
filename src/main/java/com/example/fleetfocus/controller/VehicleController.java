package com.example.fleetfocus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleetfocus.entity.Vehicle;
import com.example.fleetfocus.service.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service;
    @PreAuthorize("hasRole('FLEET_MANAGER')")
    @PostMapping("/add")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = service.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }
    @PreAuthorize("hasAnyRole('FLEET_MANAGER','DISPATCHER','DRIVER','MAINTENANCE_TECH')")
    @GetMapping("/getAll")
    public ResponseEntity<List<Vehicle>> getAllVehicle() {

        return ResponseEntity.ok(service.getAllVehicles());
    }
    @PreAuthorize("hasAnyRole('FLEET_MANAGER','DISPATCHER','DRIVER','MAINTENANCE_TECH')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getVehicleById(id));
    }
    @PreAuthorize("hasAnyRole('FLEET_MANAGER','MAINTENANCE_TECH')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id,
                                                 @Valid @RequestBody Vehicle vehicle) {

        Vehicle updatedVehicle = service.updateVehicle(id, vehicle);

        return ResponseEntity.ok(updatedVehicle);
    }
    @PreAuthorize("hasRole('FLEET_MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {

        service.deleteVehicle(id);

        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}