package com.parkinglot.observer;

import com.parkinglot.model.Vehicle;
import com.parkinglot.model.ParkingSpot;

/**
 * Concrete Observer - Display Board that shows parking lot status
 * Follows Observer Pattern implementation
 * Follows Single Responsibility Principle - handles only display logic
 */
public class DisplayBoard implements ParkingLotObserver {
    private String name;
    
    public DisplayBoard(String name) {
        this.name = name;
    }
    
    @Override
    public void onVehicleParked(Vehicle vehicle, ParkingSpot spot) {
        System.out.println("[" + name + "] Vehicle " + vehicle.getLicensePlate() + 
                          " parked at spot " + spot.getSpotNumber());
    }
    
    @Override
    public void onVehicleRemoved(Vehicle vehicle, ParkingSpot spot) {
        System.out.println("[" + name + "] Vehicle " + vehicle.getLicensePlate() + 
                          " removed from spot " + spot.getSpotNumber());
    }
    
    @Override
    public void onParkingLotFull() {
        System.out.println("[" + name + "] PARKING LOT IS FULL!");
    }
    
    @Override
    public void onParkingLotAvailable() {
        System.out.println("[" + name + "] Parking lot has available spots");
    }
}
