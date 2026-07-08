package com.example.fleetfocus.service;

import com.example.fleetfocus.entity.Trip;

public class DispatcherServiceImpl implements DispatcherService {
    private final TripService tripService;

    public DispatcherServiceImpl(TripService tripService) {
        this.tripService = tripService;
    }
    @Override
    public Trip createTrip(Long vehicleId, Long driverId) {
        return tripService.startTrip(vehicleId, driverId);
    }

    @Override
    public Trip endTrip(Long tripId, Double finalDistance) {
        return tripService.endTrip(tripId, finalDistance);
    }
}
