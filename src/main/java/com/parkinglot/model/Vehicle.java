package com.parkinglot.model;

/**
 * Abstract base class for all vehicles
 * Follows Single Responsibility Principle - handles only vehicle-related data
 * Follows Open/Closed Principle - open for extension, closed for modification
 */
public abstract class Vehicle {
    private String licensePlate;
    private VehicleType type;
    
    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    
    public VehicleType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return type + " with license plate: " + licensePlate;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return licensePlate.equals(vehicle.licensePlate);
    }
    
    @Override
    public int hashCode() {
        return licensePlate.hashCode();
    }
}
