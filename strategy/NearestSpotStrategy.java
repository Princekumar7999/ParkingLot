package com.parkinglot.strategy;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import java.util.List;


public class NearestSpotStrategy implements ParkingStrategy {
    
    @Override
    public ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> availableSpots) {
        return availableSpots.stream()
                .filter(spot -> spot.canPark(vehicle))
                .findFirst()
                .orElse(null);
    }
}
