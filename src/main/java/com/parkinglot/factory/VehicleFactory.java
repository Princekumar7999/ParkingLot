package com.parkinglot.factory;

import com.parkinglot.model.*;

/**
 * Factory Pattern for creating vehicles
 * Follows Single Responsibility Principle - handles only vehicle creation
 * Follows Open/Closed Principle - can be extended for new vehicle types
 */
public class VehicleFactory {
    
    public static Vehicle createVehicle(VehicleType type, String licensePlate) {
        switch (type) {
            case CAR:
                return new Car(licensePlate);
            case MOTORCYCLE:
                return new Motorcycle(licensePlate);
            case TRUCK:
                return new Truck(licensePlate);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
