## Elevator Simulation Project

### Description

The Elevator Simulation Project emulates the operations of a multi-elevator system within a building. Its primary goal is to illustrate the principles of concurrency and synchronization in Java. The simulation manages multiple elevators servicing various floors and handles the movement of people entering and exiting on different floors.

### Key Features

- **Concurrent Operation**: Simulates the operation of multiple elevators concurrently.
- **Passenger Handling**: Allows passengers to enter and exit elevators on different floors.
- **Dynamic Movement**: Manages elevator direction and destination based on passenger requests.
- **Capacity Management**: Tracks and ensures that each elevator doesn't exceed its passenger capacity.
- **Basic User Interface**: Provides a simple user interface to visualize elevator states and waiting passengers on each floor.

### Getting Started

#### Prerequisites

- Java Development Kit (JDK) - version 8 or higher.

#### Installation

1. Clone the repository to your local machine:
   ```sh
   git clone https://github.com/your-username/elevator-simulation.git
   ```
2. Navigate to the project directory:
   ```sh
   cd elevator-simulation
   ```

#### Running the Simulation

To run the simulation:
1. Compile the Java source files:
   ```sh
   javac com/company/*.java
   ```
2. Execute the Main class:
   ```sh
   java com.company.Main
   ```

### Usage

Upon running the simulation, you'll observe the status of each elevator and the floors being updated in real-time on the console. The simulation will continue to run indefinitely until manually stopped.

### Architecture

The project's organization includes:

- **Main.java**: Entry point of the application, responsible for initializing the building, elevators, and passengers.
- **Elevator.java**: Defines the behavior and attributes of the Elevator class.
- **Exit.java**: Simulates passengers leaving the building from different floors.
- **Floor.java**: Represents a building floor and manages the queue of passengers waiting for elevators.

### Contributing

Contributions to this project are encouraged. Here's how to contribute:

1. Fork the repository.
2. Create a new feature branch:
   ```sh
   git checkout -b feature/YourFeature
   ```
3. Commit your changes:
   ```sh
   git commit -m 'Add some feature'
   ```
4. Push the branch:
   ```sh
   git push origin feature/YourFeature
   ```
5. Submit a pull request.

### Acknowledgments

Thanks to all contributors who have aided in the development of this project. It draws inspiration from real-world elevator systems and the complexities of their operations.

--- 
