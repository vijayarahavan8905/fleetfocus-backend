package com.example.fleetfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fleetfocus.entity.Trip;
public interface TripRepo extends JpaRepository<Trip,Long> {
    
}
