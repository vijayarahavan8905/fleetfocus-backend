package com.example.fleetfocus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleetfocus.entity.Vehicle;
import com.example.fleetfocus.enums.VehicleStatus;
import com.example.fleetfocus.exception.BusinessValidationException;
import com.example.fleetfocus.exception.VehicleNotFoundException;
import com.example.fleetfocus.repository.VehicleRepo;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }
    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

        if (vehicleRepo.existsByVin(vehicle.getVin())) {
            throw new BusinessValidationException("VIN already exists");
        }

        if (vehicleRepo.existsByLicensePlate(vehicle.getLicensePlate())) {
            throw new BusinessValidationException("License plate already exists");
        }

        vehicle.setStatus(VehicleStatus.AVAILABLE);

        return vehicleRepo.save(vehicle);
    }
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }
    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepo.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
    }
    @Override
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle vehicle = vehicleRepo.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        if (vehicle.getStatus() == VehicleStatus.ON_TRIP) {
            throw new BusinessValidationException("Vehicle is currently on a trip");
        }
        vehicle.setVin(updatedVehicle.getVin());
        vehicle.setLicensePlate(updatedVehicle.getLicensePlate());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setCurrentMileage(updatedVehicle.getCurrentMileage());
        vehicle.setStatus(updatedVehicle.getStatus());

        return vehicleRepo.save(vehicle);
    }
    @Override
    public void deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        if (vehicle.getStatus() == VehicleStatus.ON_TRIP) {
            throw new BusinessValidationException("Cannot delete vehicle while on trip");
        }

        vehicleRepo.delete(vehicle);
    }
}

