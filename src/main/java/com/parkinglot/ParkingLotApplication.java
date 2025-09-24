package com.parkinglot;

import com.parkinglot.factory.VehicleFactory;
import com.parkinglot.model.*;
import com.parkinglot.observer.DisplayBoard;
import com.parkinglot.service.ParkingLotManager;
import com.parkinglot.strategy.*;

/**
 * Main Application Class demonstrating the Parking Lot System
 * Demonstrates all SOLID principles and design patterns in action
 */
public class ParkingLotApplication {
    
    public static void main(String[] args) {
        System.out.println("=== Parking Lot Management System ===\n");
        
        // Initialize parking lot with 5 car spots, 3 motorcycle spots, 2 truck spots
        ParkingLotManager parkingLot = new ParkingLotManager(5, 3, 2);
        
        // Set strategies (Strategy Pattern)
        parkingLot.setParkingStrategy(new NearestSpotStrategy());
        parkingLot.setFeeCalculationStrategy(new HourlyFeeStrategy());
        
        // Add observers (Observer Pattern)
        DisplayBoard mainDisplay = new DisplayBoard("Main Display");
        DisplayBoard entranceDisplay = new DisplayBoard("Entrance Display");
        parkingLot.addObserver(mainDisplay);
        parkingLot.addObserver(entranceDisplay);
        
        // Demonstrate parking operations
        demonstrateParking(parkingLot);
        
        // Show parking lot status
        showParkingLotStatus(parkingLot);
        
        // Demonstrate vehicle removal and fee calculation
        demonstrateVehicleRemoval(parkingLot);
        
        // Change strategy and demonstrate (Strategy Pattern flexibility)
        System.out.println("\n=== Changing to Random Spot Strategy ===");
        parkingLot.setParkingStrategy(new RandomSpotStrategy());
        
        // Park more vehicles with new strategy
        Vehicle motorcycle2 = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "BIKE-456");
        ParkingTicket ticket4 = parkingLot.parkVehicle(motorcycle2);
        if (ticket4 != null) {
            System.out.println("Parked with random strategy: " + ticket4.getTicketId());
        }
    }
    
    private static void demonstrateParking(ParkingLotManager parkingLot) {
        System.out.println("=== Parking Vehicles ===");
        
        // Create vehicles using Factory Pattern
        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "ABC-123");
        Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, "XYZ-789");
        Vehicle motorcycle1 = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "BIKE-123");
        Vehicle truck1 = VehicleFactory.createVehicle(VehicleType.TRUCK, "TRUCK-001");
        
        // Park vehicles
        ParkingTicket ticket1 = parkingLot.parkVehicle(car1);
        ParkingTicket ticket2 = parkingLot.parkVehicle(car2);
        ParkingTicket ticket3 = parkingLot.parkVehicle(motorcycle1);
        ParkingTicket ticket4 = parkingLot.parkVehicle(truck1);
        
        // Display tickets
        if (ticket1 != null) System.out.println("Ticket issued: " + ticket1.getTicketId());
        if (ticket2 != null) System.out.println("Ticket issued: " + ticket2.getTicketId());
        if (ticket3 != null) System.out.println("Ticket issued: " + ticket3.getTicketId());
        if (ticket4 != null) System.out.println("Ticket issued: " + ticket4.getTicketId());
        
        System.out.println();
    }
    
    private static void showParkingLotStatus(ParkingLotManager parkingLot) {
        System.out.println("=== Parking Lot Status ===");
        System.out.println("Total spots: " + parkingLot.getTotalSpots());
        System.out.println("Occupied spots: " + parkingLot.getOccupiedSpots());
        System.out.println("Available spots: " + parkingLot.getAvailableSpots().size());
        System.out.println("Is full: " + parkingLot.isFull());
        
        System.out.println("\nAll parking spots:");
        parkingLot.getAllSpots().forEach(System.out::println);
        System.out.println();
    }
    
    private static void demonstrateVehicleRemoval(ParkingLotManager parkingLot) {
        System.out.println("=== Removing Vehicles ===");
        
        // Simulate some time passing by manually setting exit time
        try {
            Thread.sleep(1000); // Wait 1 second to simulate time passage
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Remove a vehicle (assuming ticket TICKET-1 exists)
        try {
            double fee = parkingLot.removeVehicle("TICKET-1");
            System.out.println("Vehicle removed. Fee charged: $" + String.format("%.2f", fee));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Show updated status
        System.out.println("\nUpdated parking lot status:");
        System.out.println("Occupied spots: " + parkingLot.getOccupiedSpots());
        System.out.println("Available spots: " + parkingLot.getAvailableSpots().size());
        System.out.println();
    }
}
