package com.example.fleetfocus.service;
import java.util.List;

import com.example.fleetfocus.entity.Trip;
public interface TripService {
   Trip startTrip(Long tripId,Long driverId);
   Trip endTrip(Long tripId,Double distanceCovered); 
   List<Trip> getAllTrips();
}
