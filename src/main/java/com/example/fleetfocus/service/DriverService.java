package com.example.fleetfocus.service;
import java.util.List;

import com.example.fleetfocus.entity.Driver;
public interface DriverService {
    Driver createDriver(Long userId,String licenseNumber);
    Driver getDriverById(Long id);
    Driver updateDriver(Long id , Driver driver);
    void deleteDriver(Long id);
    List<Driver> getAllDrivers();
}
