package com.parkinglot.observer;

import com.parkinglot.model.Vehicle;
import com.parkinglot.model.ParkingSpot;

/**
 * Observer Pattern Interface for parking lot events
 * Follows Interface Segregation Principle - focused interface
 * Follows Dependency Inversion Principle - depends on abstraction
 */
public interface ParkingLotObserver {
    void onVehicleParked(Vehicle vehicle, ParkingSpot spot);
    void onVehicleRemoved(Vehicle vehicle, ParkingSpot spot);
    void onParkingLotFull();
    void onParkingLotAvailable();
}
