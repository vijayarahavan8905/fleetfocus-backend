package com.example.fleetfocus.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.fleetfocus.repository.TripRepo;
import com.example.fleetfocus.repository.DriverRepo;
import com.example.fleetfocus.repository.VehicleRepo;
import com.example.fleetfocus.entity.Driver;
import com.example.fleetfocus.entity.Trip;
import com.example.fleetfocus.enums.DriverStatus;
import com.example.fleetfocus.enums.TripStatus;
import com.example.fleetfocus.enums.VehicleStatus;
import com.example.fleetfocus.exception.BusinessValidationException;
import com.example.fleetfocus.exception.TripNotFoundException;
import com.example.fleetfocus.entity.Vehicle;
@Service
public class TripServiceImpl implements TripService {
    private final TripRepo tripRepo;
    private final DriverRepo driverRepo;
    private final VehicleRepo vehicleRepo;
    public TripServiceImpl(TripRepo tripRepo, DriverRepo driverRepo, VehicleRepo vehicleRepo) {
        this.tripRepo = tripRepo;
        this.driverRepo = driverRepo;
        this.vehicleRepo = vehicleRepo;
    }
@Override
public Trip startTrip(Long vehicleId, Long driverId) {
    Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow(() -> new RuntimeException("Vehicle not found"));
    Driver driver = driverRepo.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
    if (vehicle.getStatus() != VehicleStatus.AVAILABLE) {
        throw new BusinessValidationException("Vehicle is already in use");
    }
    if (driver.getStatus() != DriverStatus.AVAILABLE) {
        throw new BusinessValidationException("Driver is already on another trip");
    }
    Trip trip = new Trip();
    trip.setVehicle(vehicle);
    trip.setDriver(driver);
    trip.setStartTime(LocalDateTime.now());
    trip.setStatus(TripStatus.ACTIVE);
    driver.setStatus(DriverStatus.ON_TRIP);
    vehicle.setStatus(VehicleStatus.ON_TRIP);
    driverRepo.save(driver);
    vehicleRepo.save(vehicle);
    return tripRepo.save(trip);
}
    @Override
    public Trip endTrip(Long tripId,Double distanceCovered){
        Trip trip=tripRepo.findById(tripId).orElseThrow(()->new TripNotFoundException("Trip Not Found"));
        if (trip.getStatus() == TripStatus.COMPLETED) {
            throw new BusinessValidationException("Trip already completed");
        }
        trip.setStatus(TripStatus.COMPLETED);
        trip.setEndTime(LocalDateTime.now());
        trip.setDistanceCovered(distanceCovered);
        Driver driver=trip.getDriver();
        driver.setStatus(DriverStatus.AVAILABLE);
        driverRepo.save(driver);
        Vehicle vehicle=trip.getVehicle();
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicleRepo.save(vehicle);

        return tripRepo.save(trip);
    }
    @Override
    public List<Trip> getAllTrips(){
        return tripRepo.findAll();
    }
}
