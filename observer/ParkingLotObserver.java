package com.parkinglot.observer;

import com.parkinglot.model.Vehicle;
import com.parkinglot.model.ParkingSpot;


public interface ParkingLotObserver {
    void onVehicleParked(Vehicle vehicle, ParkingSpot spot);
    void onVehicleRemoved(Vehicle vehicle, ParkingSpot spot);
    void onParkingLotFull();
    void onParkingLotAvailable();
}
