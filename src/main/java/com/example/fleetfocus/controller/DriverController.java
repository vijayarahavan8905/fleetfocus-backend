package com.example.fleetfocus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleetfocus.entity.Driver;
import com.example.fleetfocus.service.DriverService;
@RestController
@RequestMapping("/driver")
public class DriverController {
    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }
    @PreAuthorize("hasRole('FLEET_MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<Driver> createDriver(@RequestParam Long userId,@RequestParam String licenseNumber){
        return ResponseEntity.ok(service.createDriver(userId, licenseNumber));
    }
    @PreAuthorize("hasAnyRole('FLEET_MANAGER','DISPATCHER')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDriverById(id));
    }
    @PreAuthorize("hasRole('FLEET_MANAGER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Driver> updateDriver(
            @PathVariable Long id,
            @RequestBody Driver driver) {

        return ResponseEntity.ok(service.updateDriver(id, driver));
    }
    @PreAuthorize("hasRole('FLEET_MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long id) {
        service.deleteDriver(id);
        return ResponseEntity.ok("Driver deleted successfully");
    }
    @PreAuthorize("hasAnyRole('FLEET_MANAGER','DISPATCHER','DRIVER')")
    @GetMapping("/getAll")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(service.getAllDrivers());
    }
}
