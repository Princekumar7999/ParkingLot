# Parking Lot Management System

A comprehensive parking lot management system implemented in Java.

##  Architecture Overview

This system is designed with clean architecture principles, ensuring maintainability, extensibility, and testability.

##  Class Diagram

```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                           PARKING LOT SYSTEM CLASS DIAGRAM                      │
└─────────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────┐    ┌─────────────────────┐    ┌─────────────────────┐
│      Vehicle        │    │    VehicleType      │    │   ParkingSpot       │
│    (Abstract)       │    │     (Enum)          │    │                     │
├─────────────────────┤    ├─────────────────────┤    ├─────────────────────┤
│ - licensePlate      │    │ CAR(1.0)            │    │ - spotNumber        │
│ - type              │    │ MOTORCYCLE(0.5)     │    │ - allowedVehicleType│
├─────────────────────┤    │ TRUCK(2.0)          │    │ - parkedVehicle     │
│ + getLicensePlate() │    │                     │    │ - isOccupied        │
│ + getType()         │    │ + getSizeMultiplier()│    ├─────────────────────┤
│ + toString()        │    └─────────────────────┘    │ + canPark()         │
│ + equals()          │                               │ + parkVehicle()     │
│ + hashCode()        │                               │ + removeVehicle()   │
└─────────────────────┘                               └─────────────────────┘
         △                                                       △
         │                                                       │
    ┌────┴────┐                                                 │
    │         │                                                 │
┌───▼───┐ ┌──▼──┐ ┌─────▼─────┐                               │
│  Car  │ │Truck│ │Motorcycle │                               │
└───────┘ └─────┘ └───────────┘                               │
                                                               │
┌─────────────────────┐    ┌─────────────────────┐            │
│   ParkingTicket     │    │  VehicleFactory     │            │
│                     │    │   (Factory)         │            │
├─────────────────────┤    ├─────────────────────┤            │
│ - ticketId          │    │ + createVehicle()   │◄───────────┘
│ - vehicle           │    │   (static)          │
│ - parkingSpot       │    └─────────────────────┘
│ - entryTime         │
│ - exitTime          │    ┌─────────────────────┐
│ - fee               │    │ ParkingStrategy     │
├─────────────────────┤    │   (Interface)       │
│ + setExitTime()     │    ├─────────────────────┤
│ + setFee()          │    │ + findParkingSpot() │
│ + getters...        │    └─────────────────────┘
└─────────────────────┘             △
                                    │
                        ┌───────────┴───────────┐
                        │                       │
            ┌───────────▼───────────┐ ┌────────▼────────┐
            │ NearestSpotStrategy   │ │RandomSpotStrategy│
            │                       │ │                 │
            ├───────────────────────┤ ├─────────────────┤
            │ + findParkingSpot()   │ │+ findParkingSpot│
            └───────────────────────┘ └─────────────────┘

┌─────────────────────┐    ┌─────────────────────┐
│FeeCalculationStrategy│    │ParkingLotObserver   │
│   (Interface)       │    │   (Interface)       │
├─────────────────────┤    ├─────────────────────┤
│ + calculateFee()    │    │ + onVehicleParked() │
└─────────────────────┘    │ + onVehicleRemoved()│
         △                 │ + onParkingLotFull()│
         │                 │ + onParkingLotAvail()│
┌────────▼────────┐        └─────────────────────┘
│HourlyFeeStrategy│                 △
│                 │                 │
├─────────────────┤        ┌────────▼────────┐
│+ calculateFee() │        │  DisplayBoard   │
└─────────────────┘        │                 │
                           ├─────────────────┤
┌─────────────────────────────────────────┐  │ - name          │
│         ParkingLotManager               │  │ + onVehicle...()│
│                                         │  └─────────────────┘
├─────────────────────────────────────────┤
│ - parkingSpots: List<ParkingSpot>       │
│ - activeTickets: Map<String,Ticket>     │
│ - observers: List<ParkingLotObserver>   │
│ - parkingStrategy: ParkingStrategy      │
│ - feeCalculationStrategy: FeeCalcStrat  │
├─────────────────────────────────────────┤
│ + parkVehicle(Vehicle): ParkingTicket   │
│ + removeVehicle(String): double         │
│ + setParkingStrategy(ParkingStrategy)   │
│ + setFeeCalculationStrategy(FeeCalcStr) │
│ + addObserver(ParkingLotObserver)       │
│ + getAvailableSpots(): List<ParkingSpot>│
│ + isFull(): boolean                     │
└─────────────────────────────────────────┘
```

##  Project Structure

```
ParkingLotSystem/
├── src/main/java/com/parkinglot/
│   ├── model/
│   │   ├── Vehicle.java              # Abstract vehicle class
│   │   ├── VehicleType.java          # Vehicle type enumeration
│   │   ├── Car.java                  # Car implementation
│   │   ├── Motorcycle.java           # Motorcycle implementation
│   │   ├── Truck.java                # Truck implementation
│   │   ├── ParkingSpot.java          # Parking spot entity
│   │   └── ParkingTicket.java        # Parking ticket entity
│   ├── strategy/
│   │   ├── ParkingStrategy.java      # Parking strategy interface
│   │   ├── NearestSpotStrategy.java  # Nearest spot implementation
│   │   ├── RandomSpotStrategy.java   # Random spot implementation
│   │   ├── FeeCalculationStrategy.java # Fee calculation interface
│   │   └── HourlyFeeStrategy.java    # Hourly fee implementation
│   ├── factory/
│   │   └── VehicleFactory.java       # Vehicle factory
│   ├── observer/
│   │   ├── ParkingLotObserver.java   # Observer interface
│   │   └── DisplayBoard.java         # Display board observer
│   ├── service/
│   │   └── ParkingLotManager.java    # Main parking lot manager
│   └── ParkingLotApplication.java    # Main application
└── README.md                         # This file
```

##  How to Run

### Prerequisites
- Java 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)
- Optional: Apache Maven 3.8+

### Steps to Run

1. **Clone or Download** the project to your local machine

2. **Navigate to the project directory**:
   ```bash
   cd C:\Users\Scaler\CascadeProjects\ParkingLotSystem
   ```
3. Run without Maven (Windows):
   - Use the provided script for one-click compile and run:
     ```bat
     run.bat
     ```
   - Or compile manually:
     ```bash
     javac -d out src/main/java/com/parkinglot/ParkingLotApplication.java src/main/java/com/parkinglot/model/*.java src/main/java/com/parkinglot/strategy/*.java src/main/java/com/parkinglot/factory/*.java src/main/java/com/parkinglot/observer/*.java src/main/java/com/parkinglot/service/*.java
     java -cp out com.parkinglot.ParkingLotApplication
     ```

### Alternative: Using IDE
1. Open the project in your preferred Java IDE
2. Set the source folder to `src/main/java`
3. Run the `ParkingLotApplication.java` file



##  Function Explanations

### Core Classes

#### `Vehicle` (Abstract Class)
- **Purpose**: Base class for all vehicles
- **Key Methods**:
  - `getLicensePlate()`: Returns vehicle license plate
  - `getType()`: Returns vehicle type
  - `equals()` & `hashCode()`: For proper object comparison

#### `ParkingSpot`
- **Purpose**: Represents a parking spot
- **Key Methods**:
  - `canPark(Vehicle)`: Checks if vehicle can park in this spot
  - `parkVehicle(Vehicle)`: Parks a vehicle in the spot
  - `removeVehicle()`: Removes vehicle from spot

#### `ParkingLotManager`
- **Purpose**: Main coordinator for parking operations
- **Key Methods**:
  - `parkVehicle(Vehicle)`: Parks a vehicle and returns ticket
  - `removeVehicle(String)`: Removes vehicle using ticket ID
  - `setParkingStrategy()`: Sets parking spot assignment strategy
  - `setFeeCalculationStrategy()`: Sets fee calculation method
  - `addObserver()`: Adds notification observer

### Strategy Classes

#### `NearestSpotStrategy`
- **Purpose**: Finds the nearest available parking spot
- **Algorithm**: Returns first available spot that matches vehicle type

#### `RandomSpotStrategy`  
- **Purpose**: Finds a random available parking spot
- **Algorithm**: Randomly selects from available spots matching vehicle type

#### `HourlyFeeStrategy`
- **Purpose**: Calculates fees based on hourly rates
- **Algorithm**: Base rate × hours × vehicle size multiplier

### Observer Classes

#### `DisplayBoard`
- **Purpose**: Shows parking lot events on display boards
- **Events Handled**:
  - Vehicle parked/removed notifications
  - Parking lot full/available notifications


This parking lot system demonstrates software design principles.
