package com.example.fleetfocus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleetfocus.entity.Driver;
import com.example.fleetfocus.entity.SystemUser;
import com.example.fleetfocus.enums.DriverStatus;
import com.example.fleetfocus.enums.Role;
import com.example.fleetfocus.exception.BusinessValidationException;
import com.example.fleetfocus.exception.UserNotFoundException;
import com.example.fleetfocus.repository.DriverRepo;
import com.example.fleetfocus.repository.SystemUserRepo;

@Service
public class DriverServiceImpl implements DriverService{
    private final DriverRepo repo;
    private final SystemUserRepo userRepo;
    public DriverServiceImpl(DriverRepo repo, SystemUserRepo userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }
    @Override
    public Driver createDriver(Long userId,String licenseNumber){
        SystemUser user=userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        if (user.getRole() != Role.DRIVER) {
            throw new BusinessValidationException("User is not assigned DRIVER role");
        }

        if (repo.existsByLicenseNumber(licenseNumber)) {
            throw new BusinessValidationException("License number already exists");
        }
        Driver driver=new Driver();
        driver.setUser(user);
        driver.setLicenseNumber(licenseNumber);
        driver.setStatus(DriverStatus.OFF_DUTY);
        return repo.save(driver);
    }
    @Override
    public Driver getDriverById(Long id){
        return repo.findById(id).orElseThrow(()->new UserNotFoundException("Driver not found"));
    }
    @Override
    public Driver updateDriver(Long id, Driver driver) {
            Driver existing = repo.findById(id).orElseThrow(() -> new UserNotFoundException("Driver not found"));
            if (driver.getStatus() == DriverStatus.ON_TRIP) {
            throw new BusinessValidationException("Driver is currently on a trip");
        }
            existing.setLicenseNumber(driver.getLicenseNumber());
            existing.setStatus(driver.getStatus());
            return repo.save(existing);
    }
    @Override
    public void deleteDriver(Long id) {
        Driver driver = repo.findById(id).orElseThrow(() -> new RuntimeException("Driver not found"));
        if (driver.getStatus() == DriverStatus.ON_TRIP) {
            throw new BusinessValidationException("Cannot delete driver during active trip");
        }
        repo.delete(driver);
    }
    @Override
    public List<Driver> getAllDrivers(){
        return repo.findAll();
    }
}
