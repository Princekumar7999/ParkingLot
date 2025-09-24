package com.parkinglot.strategy;

import com.parkinglot.model.ParkingTicket;


public interface FeeCalculationStrategy {
    double calculateFee(ParkingTicket ticket);
}
