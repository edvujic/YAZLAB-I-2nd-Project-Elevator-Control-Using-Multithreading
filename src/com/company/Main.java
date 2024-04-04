package com.company;

import java.util.ArrayList;

/**
 * The Main class that sets up the floors, elevators, and threads for the simulation.
 */
public class Main {

    public static final int FLOOR_NUM = 5;
    public static final int ELEVATOR_NUM = 5;
    public static ArrayList<Floor> floors = new ArrayList<>();
    public static ArrayList<Elevator> elevators = new ArrayList<>();
    public static Thread[] threads = new Thread[ELEVATOR_NUM];
    public static int exitCount = 0;

    /**
     * The entry point of the application.
     * Initializes floors, elevators, and threads, and starts the simulation threads.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        initializeFloors();
        initializeElevator();
        initializeThreadArray();

        Thread login = new Thread(new Login());
        login.start();

        Thread info = new Thread(new Info());
        info.start();

        Thread control = new Thread(new Control());
        control.start();

        Thread exit = new Thread(new Exit());
        exit.start();

        threads[0].start();
    }

    /**
     * Initializes the floors for the simulation.
     */
    public static void initializeFloors() {
        for (int i = 0; i < FLOOR_NUM; i++) {
            floors.add(new Floor(i, 0, new ArrayList<>()));
        }
    }

    /**
     * Initializes the elevators for the simulation.
     */
    public static void initializeElevator() {
        for (int i = 0; i < ELEVATOR_NUM; i++) {
            elevators.add(new Elevator(i, "IDLE", 0, 0, "up", 10, 0, new ArrayList<>(), false));
        }
    }

    /**
     * Initializes the array of threads for the elevators.
     */
    public static void initializeThreadArray() {
        for (int i = 0; i < ELEVATOR_NUM; i++) {
            threads[i] = new Thread(elevators.get(i));
        }
    }

    /**
     * Starts a new elevator thread or resumes it if it has already been started.
     *
     * @param liftCount    the index of the elevator to start or resume
     * @param destination  the destination floor for the elevator
     */
    public static void startNewLift(int liftCount, int destination) {
        if ("NEW".equals(threads[liftCount].getState().toString())) {
            elevators.get(liftCount).destination = destination;
            threads[liftCount].start();
        } else {
            elevators.get(liftCount).destination = destination;
            threads[liftCount].resume();
        }
    }
}
