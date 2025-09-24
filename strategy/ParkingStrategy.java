package com.parkinglot.strategy;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import java.util.List;


public interface ParkingStrategy {
    ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> availableSpots);
}
