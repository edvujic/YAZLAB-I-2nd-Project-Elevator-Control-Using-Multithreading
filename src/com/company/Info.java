package com.company;

import java.io.IOException;
import static com.company.Main.*;

/**
 * The Info class implements the Runnable interface and is responsible for printing
 * the simulation's status information to the console.
 */
public class Info implements Runnable {

    /**
     * Continuously prints the status information of floors, elevators, and people waiting in queues.
     */
    @Override
    public void run() {
        while (true) {
            printFloors();
            System.out.println("Exit count: " + exitCount);
            System.out.println();
            printElevators();
            System.out.println();
            printPersonNum();
            System.out.println();

            try {
                // Clears the console after printing the information
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints information about each floor.
     */
    public static void printFloors() {
        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).printFloor();
        }
    }

    /**
     * Prints information about each elevator.
     */
    public static void printElevators() {
        for (int i = 0; i < elevators.size(); i++) {
            elevators.get(i).printElevator();
        }
    }

    /**
     * Prints the number of people waiting at each floor's queue.
     */
    public static void printPersonNum() {
        for (int i = 0; i < floors.size(); i++) {
            int sum = 0;
            for (int j = 0; j < floors.get(i).waitingAtQueue.size(); j++) {
                sum += (Integer) floors.get(i).waitingAtQueue.get(j).get(0);
            }
            if (i == 0) {
                System.out.println(i + ". floor -> queue: " + sum);
            } else {
                System.out.println(i + ". floor -> all: " + floors.get(i).numOfPeopleOnFloor + " queue: " + sum);
            }
        }
    }
}