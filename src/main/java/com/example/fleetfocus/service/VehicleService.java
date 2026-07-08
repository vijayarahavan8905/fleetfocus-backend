package com.example.fleetfocus.service;

import java.util.List;

import com.example.fleetfocus.entity.Vehicle;

public interface VehicleService {
    List<Vehicle> getAllVehicles();  //get all vehicle
    Vehicle getVehicleById(Long id);       //get by id 
    Vehicle createVehicle(Vehicle vehicle); //create new vehicle
    Vehicle updateVehicle(Long id,Vehicle vehicle);  //update vehicle
    void deleteVehicle(Long id);    //Delete Vehicle
}
