package com.parkinglot.strategy;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import java.util.List;

/**
 * Concrete Strategy - finds the nearest available parking spot
 * Follows Strategy Pattern implementation
 * Follows Single Responsibility Principle - handles only nearest spot logic
 */
public class NearestSpotStrategy implements ParkingStrategy {
    
    @Override
    public ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> availableSpots) {
        return availableSpots.stream()
                .filter(spot -> spot.canPark(vehicle))
                .findFirst()
                .orElse(null);
    }
}
