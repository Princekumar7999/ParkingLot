package com.parkinglot.model;

/**
 * Motorcycle implementation of Vehicle
 * Follows Liskov Substitution Principle - can be substituted for Vehicle
 */
public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }
}
