package com.parkinglot.model;

/**
 * Enum for different vehicle types
 * Follows Single Responsibility Principle - defines vehicle types only
 */
public enum VehicleType {
    CAR(1),
    MOTORCYCLE(0.5),
    TRUCK(2);
    
    private final double sizeMultiplier;
    
    VehicleType(double sizeMultiplier) {
        this.sizeMultiplier = sizeMultiplier;
    }
    
    public double getSizeMultiplier() {
        return sizeMultiplier;
    }
}
