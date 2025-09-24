package com.parkinglot.strategy;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import java.util.List;

/**
 * Strategy Pattern Interface for parking spot assignment
 * Follows Interface Segregation Principle - single focused interface
 * Follows Dependency Inversion Principle - depends on abstraction
 */
public interface ParkingStrategy {
    ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> availableSpots);
}
