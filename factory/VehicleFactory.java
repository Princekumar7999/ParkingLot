package com.parkinglot.factory;

import com.parkinglot.model.*;

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
