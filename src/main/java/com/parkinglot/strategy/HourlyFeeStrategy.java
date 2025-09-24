package com.parkinglot.strategy;

import com.parkinglot.model.ParkingTicket;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Concrete Strategy - calculates fee based on hourly rates
 * Follows Strategy Pattern implementation
 * Follows Single Responsibility Principle - handles only hourly fee calculation
 */
public class HourlyFeeStrategy implements FeeCalculationStrategy {
    private static final double BASE_RATE = 2.0; // $2 per hour
    
    @Override
    public double calculateFee(ParkingTicket ticket) {
        if (ticket.getExitTime() == null) {
            return 0.0;
        }
        
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        long hours = duration.toHours();
        if (duration.toMinutes() % 60 > 0) {
            hours++; // Round up to next hour
        }
        
        double multiplier = ticket.getVehicle().getType().getSizeMultiplier();
        return hours * BASE_RATE * multiplier;
    }
}
