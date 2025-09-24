package com.parkinglot.strategy;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Concrete Strategy - finds a random available parking spot
 * Follows Strategy Pattern implementation
 * Follows Single Responsibility Principle - handles only random spot logic
 */
public class RandomSpotStrategy implements ParkingStrategy {
    private final Random random = new Random();
    
    @Override
    public ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> availableSpots) {
        List<ParkingSpot> suitableSpots = availableSpots.stream()
                .filter(spot -> spot.canPark(vehicle))
                .collect(Collectors.toList());
        
        if (suitableSpots.isEmpty()) {
            return null;
        }
        
        return suitableSpots.get(random.nextInt(suitableSpots.size()));
    }
}
