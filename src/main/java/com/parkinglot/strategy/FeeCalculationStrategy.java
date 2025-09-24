package com.parkinglot.strategy;

import com.parkinglot.model.ParkingTicket;

/**
 * Strategy Pattern Interface for fee calculation
 * Follows Interface Segregation Principle - single focused interface
 * Follows Dependency Inversion Principle - depends on abstraction
 */
public interface FeeCalculationStrategy {
    double calculateFee(ParkingTicket ticket);
}
