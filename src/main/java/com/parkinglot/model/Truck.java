package com.parkinglot.model;

/**
 * Truck implementation of Vehicle
 * Follows Liskov Substitution Principle - can be substituted for Vehicle
 */
public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
}
