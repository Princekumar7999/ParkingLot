package com.parkinglot.model;

import java.time.LocalDateTime;

/**
 * Represents a parking ticket issued when a vehicle is parked
 * Follows Single Responsibility Principle - manages ticket information only
 */
public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fee;
    
    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
        this.exitTime = null;
        this.fee = 0.0;
    }
    
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
    
    public void setFee(double fee) {
        this.fee = fee;
    }
    
    // Getters
    public String getTicketId() {
        return ticketId;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
    
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    
    public LocalDateTime getExitTime() {
        return exitTime;
    }
    
    public double getFee() {
        return fee;
    }
    
    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + 
               "\nVehicle: " + vehicle + 
               "\nSpot: " + parkingSpot.getSpotNumber() + 
               "\nEntry Time: " + entryTime +
               (exitTime != null ? "\nExit Time: " + exitTime : "") +
               (fee > 0 ? "\nFee: $" + fee : "");
    }
}
