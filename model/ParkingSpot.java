package com.parkinglot.model;


public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType allowedVehicleType;
    private Vehicle parkedVehicle;
    private boolean isOccupied;
    
    public ParkingSpot(int spotNumber, VehicleType allowedVehicleType) {
        this.spotNumber = spotNumber;
        this.allowedVehicleType = allowedVehicleType;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }
    
    public boolean canPark(Vehicle vehicle) {
        return !isOccupied && vehicle.getType() == allowedVehicleType;
    }
    
    public boolean parkVehicle(Vehicle vehicle) {
        if (canPark(vehicle)) {
            this.parkedVehicle = vehicle;
            this.isOccupied = true;
            return true;
        }
        return false;
    }
    
    public Vehicle removeVehicle() {
        if (isOccupied) {
            Vehicle vehicle = this.parkedVehicle;
            this.parkedVehicle = null;
            this.isOccupied = false;
            return vehicle;
        }
        return null;
    }
    
    // Getters
    public int getSpotNumber() {
        return spotNumber;
    }
    
    public VehicleType getAllowedVehicleType() {
        return allowedVehicleType;
    }
    
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
    @Override
    public String toString() {
        return "Spot " + spotNumber + " (" + allowedVehicleType + ")" + 
               (isOccupied ? " - OCCUPIED by " + parkedVehicle : " - AVAILABLE");
    }
}
