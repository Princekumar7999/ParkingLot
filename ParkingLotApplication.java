package com.parkinglot;

import com.parkinglot.factory.VehicleFactory;
import com.parkinglot.model.*;
import com.parkinglot.observer.DisplayBoard;
import com.parkinglot.service.ParkingLotManager;
import com.parkinglot.strategy.*;


public class ParkingLotApplication {
    
    public static void main(String[] args) {
        System.out.println("=== Parking Lot Management System ===\n");
        
        
        ParkingLotManager parkingLot = new ParkingLotManager(5, 3, 2);
        
        
        parkingLot.setParkingStrategy(new NearestSpotStrategy());
        parkingLot.setFeeCalculationStrategy(new HourlyFeeStrategy());
        
        
        DisplayBoard mainDisplay = new DisplayBoard("Main Display");
        DisplayBoard entranceDisplay = new DisplayBoard("Entrance Display");
        parkingLot.addObserver(mainDisplay);
        parkingLot.addObserver(entranceDisplay);
        
        
        demonstrateParking(parkingLot);
        
        
        showParkingLotStatus(parkingLot);
        
        
        demonstrateVehicleRemoval(parkingLot);
        
        
        System.out.println("\n=== Changing to Random Spot Strategy ===");
        parkingLot.setParkingStrategy(new RandomSpotStrategy());
        
        
        Vehicle motorcycle2 = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "BIKE-456");
        ParkingTicket ticket4 = parkingLot.parkVehicle(motorcycle2);
        if (ticket4 != null) {
            System.out.println("Parked with random strategy: " + ticket4.getTicketId());
        }
    }
    
    private static void demonstrateParking(ParkingLotManager parkingLot) {
        System.out.println("=== Parking Vehicles ===");
        
        
        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "ABC-123");
        Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, "XYZ-789");
        Vehicle motorcycle1 = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "BIKE-123");
        Vehicle truck1 = VehicleFactory.createVehicle(VehicleType.TRUCK, "TRUCK-001");
        
        
        ParkingTicket ticket1 = parkingLot.parkVehicle(car1);
        ParkingTicket ticket2 = parkingLot.parkVehicle(car2);
        ParkingTicket ticket3 = parkingLot.parkVehicle(motorcycle1);
        ParkingTicket ticket4 = parkingLot.parkVehicle(truck1);
        
    
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
        
        
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        
        try {
            double fee = parkingLot.removeVehicle("TICKET-1");
            System.out.println("Vehicle removed. Fee charged: $" + String.format("%.2f", fee));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
        System.out.println("\nUpdated parking lot status:");
        System.out.println("Occupied spots: " + parkingLot.getOccupiedSpots());
        System.out.println("Available spots: " + parkingLot.getAvailableSpots().size());
        System.out.println();
    }
}
