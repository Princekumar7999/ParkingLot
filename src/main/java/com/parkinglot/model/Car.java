package com.parkinglot.model;

/**
 * Car implementation of Vehicle
 * Follows Liskov Substitution Principle - can be substituted for Vehicle
 */
public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
}
