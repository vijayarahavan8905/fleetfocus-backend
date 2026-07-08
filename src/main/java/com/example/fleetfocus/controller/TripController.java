package com.example.fleetfocus.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleetfocus.entity.Trip;
import com.example.fleetfocus.service.TripService;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService service;

    public TripController(TripService service) {
        this.service = service;
    }
    @PreAuthorize("hasAnyRole('FLEET_MANAGER','DISPATCHER')")
    @GetMapping("/getAll")
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(service.getAllTrips());
    }
    @PreAuthorize("hasRole('DISPATCHER')")
    @PostMapping("/startTrip")
    public ResponseEntity<Trip> startTrip(
            @RequestParam Long vehicleId,
            @RequestParam Long driverId) {

        return ResponseEntity.ok(service.startTrip(vehicleId, driverId));
    }
    @PreAuthorize("hasRole('DISPATCHER')")
    @PutMapping("/endTrip")
    public ResponseEntity<Trip> endTrip(
            @RequestParam Long tripId,
            @RequestParam Double distance) {

        return ResponseEntity.ok(service.endTrip(tripId, distance));
    }
}
