package com.example.fleetfocus.service;
import com.example.fleetfocus.entity.Trip;
public interface DispatcherService {
    Trip createTrip(Long vehicleId,Long driverId);
    Trip endTrip(Long tripId,Double distanceCovered);
}
