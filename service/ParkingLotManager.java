package com.parkinglot.service;

import com.parkinglot.model.*;
import com.parkinglot.observer.ParkingLotObserver;
import com.parkinglot.strategy.FeeCalculationStrategy;
import com.parkinglot.strategy.ParkingStrategy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ParkingLotManager {
    private final List<ParkingSpot> parkingSpots;
    private final Map<String, ParkingTicket> activeTickets;
    private final List<ParkingLotObserver> observers;
    private final AtomicInteger ticketCounter;
    private ParkingStrategy parkingStrategy;
    private FeeCalculationStrategy feeCalculationStrategy;
    
    public ParkingLotManager(int carSpots, int motorcycleSpots, int truckSpots) {
        this.parkingSpots = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.observers = new ArrayList<>();
        this.ticketCounter = new AtomicInteger(1);
        
        initializeParkingSpots(carSpots, motorcycleSpots, truckSpots);
    }
    
    private void initializeParkingSpots(int carSpots, int motorcycleSpots, int truckSpots) {
        int spotNumber = 1;
        
        
        for (int i = 0; i < carSpots; i++) {
            parkingSpots.add(new ParkingSpot(spotNumber++, VehicleType.CAR));
        }
        
        
        for (int i = 0; i < motorcycleSpots; i++) {
            parkingSpots.add(new ParkingSpot(spotNumber++, VehicleType.MOTORCYCLE));
        }
        
        
        for (int i = 0; i < truckSpots; i++) {
            parkingSpots.add(new ParkingSpot(spotNumber++, VehicleType.TRUCK));
        }
    }
    
    public void setParkingStrategy(ParkingStrategy strategy) {
        this.parkingStrategy = strategy;
    }
    
    public void setFeeCalculationStrategy(FeeCalculationStrategy strategy) {
        this.feeCalculationStrategy = strategy;
    }
    
    public void addObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(ParkingLotObserver observer) {
        observers.remove(observer);
    }
    
    public ParkingTicket parkVehicle(Vehicle vehicle) {
        if (parkingStrategy == null) {
            throw new IllegalStateException("Parking strategy not set");
        }
        
        List<ParkingSpot> availableSpots = getAvailableSpots();
        ParkingSpot spot = parkingStrategy.findParkingSpot(vehicle, availableSpots);
        
        if (spot == null) {
            notifyParkingLotFull();
            return null;
        }
        
        if (spot.parkVehicle(vehicle)) {
            String ticketId = "TICKET-" + ticketCounter.getAndIncrement();
            ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, spot);
            activeTickets.put(ticketId, ticket);
            
            notifyVehicleParked(vehicle, spot);
            return ticket;
        }
        
        return null;
    }
    
    public double removeVehicle(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Invalid ticket ID: " + ticketId);
        }
        
        ticket.setExitTime(LocalDateTime.now());
        
        if (feeCalculationStrategy != null) {
            double fee = feeCalculationStrategy.calculateFee(ticket);
            ticket.setFee(fee);
        }
        
        Vehicle vehicle = ticket.getParkingSpot().removeVehicle();
        activeTickets.remove(ticketId);
        
        notifyVehicleRemoved(vehicle, ticket.getParkingSpot());
        notifyParkingLotAvailable();
        
        return ticket.getFee();
    }
    
    public List<ParkingSpot> getAvailableSpots() {
        return parkingSpots.stream()
                .filter(spot -> !spot.isOccupied())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    public List<ParkingSpot> getAllSpots() {
        return new ArrayList<>(parkingSpots);
    }
    
    public ParkingTicket getTicket(String ticketId) {
        return activeTickets.get(ticketId);
    }
    
    public boolean isFull() {
        return getAvailableSpots().isEmpty();
    }
    
    public int getOccupiedSpots() {
        return (int) parkingSpots.stream().filter(ParkingSpot::isOccupied).count();
    }
    
    public int getTotalSpots() {
        return parkingSpots.size();
    }
    
    
    private void notifyVehicleParked(Vehicle vehicle, ParkingSpot spot) {
        observers.forEach(observer -> observer.onVehicleParked(vehicle, spot));
    }
    
    private void notifyVehicleRemoved(Vehicle vehicle, ParkingSpot spot) {
        observers.forEach(observer -> observer.onVehicleRemoved(vehicle, spot));
    }
    
    private void notifyParkingLotFull() {
        observers.forEach(ParkingLotObserver::onParkingLotFull);
    }
    
    private void notifyParkingLotAvailable() {
        if (!isFull()) {
            observers.forEach(ParkingLotObserver::onParkingLotAvailable);
        }
    }
}
