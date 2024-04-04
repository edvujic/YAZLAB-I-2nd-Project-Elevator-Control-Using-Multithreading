package com.company;

import java.util.ArrayList;

import static com.company.Main.*;

/**
 * Represents an elevator in a building.
 */
public class Elevator implements Runnable {

    /** The ID of the elevator. */
    int elevatorID;
    /** The mode of the elevator (e.g., ACTIVE, IDLE). */
    String mode;
    /** The current floor of the elevator. */
    int floor;
    /** The destination floor of the elevator. */
    int destination;
    /** The direction the elevator is moving (up, down). */
    String direction;
    /** The maximum capacity of the elevator. */
    int capacity;
    /** The number of people inside the elevator. */
    int count_inside;
    /** The list of people inside the elevator. */
    ArrayList<ArrayList> inside;
    /** Indicates whether the elevator is full. */
    boolean isFull;
    /** Indicates whether the elevator needs to be set idle. */
    boolean makeIdle = false;


    public Elevator(int elevatorID, String mode, int floor, int destination, String direction, int capacity, int count_inside, ArrayList inside, boolean isFull) {
        this.elevatorID = elevatorID;
        this.mode = mode;
        this.floor = floor;
        this.destination = destination;
        this.direction = direction;
        this.capacity = capacity;
        this.count_inside = count_inside;
        this.inside = inside;
        this.isFull = isFull;
    }

    @Override
    public void run() {
        while (true) {
            isStopElevator();
            if (elevators.get(0).inside.size() == 0) {
                elevators.get(0).destination = 0;
            }
            if (this.floor < this.destination) {
                goingUp();
            } else if (this.floor == this.destination) {
                leaveCustomers();
                takeCustomers();
                setElevatorDestination();
            } else {
                goingDown();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void goingUp() {
        this.direction = "up";
        this.floor++;
        this.mode = "ACTIVE";
    }

    public void goingDown() {
        this.direction = "down";
        this.floor--;
        this.mode = "ACTIVE";
    }

    public void printElevator() {
        System.out.println("ID: " + this.elevatorID
                + " \nmode: " + this.mode
                + " \n\t\t\t\tfloor: " + this.floor
                + " \n\t\t\t\tdestination: " + this.destination
                + " \n\t\t\t\tdirection: " + this.direction
                + " \n\t\t\t\tcapacity: " + this.capacity
                + " \n\t\t\t\tcount_inside: " + this.count_inside
                + " \n\t\t\t\tinside: " + this.inside
                + " \n\t\t\t\tmakeIdle: " + this.makeIdle);
    }

    public void takeCustomers() {
        for (int i = 0; i < floors.get(this.floor).waitingAtQueue.size(); i++) {
            if (this.count_inside + (Integer) floors.get(this.floor).waitingAtQueue.get(i).get(0) <= this.capacity) {
                this.inside.add(floors.get(this.floor).waitingAtQueue.get(i));
                this.count_inside += (Integer) floors.get(this.floor).waitingAtQueue.get(i).get(0);
                floors.get(this.floor).numOfPeopleOnFloor -= (Integer) floors.get(this.floor).waitingAtQueue.get(i).get(0);
                floors.get(this.floor).waitingAtQueue.remove(i);
            }
        }
    }

    public void leaveCustomers() {
        for (int i = 0; i < this.inside.size(); i++) {
            if ((Integer) this.inside.get(i).get(1) == this.floor) {
                if ((Integer) this.inside.get(i).get(1) != 0) {
                    floors.get(this.floor).numOfPeopleOnFloor += (Integer) this.inside.get(i).get(0);
                } else {
                    exitCount += (Integer) this.inside.get(i).get(0);
                }
                if(this.count_inside>0)
                    this.count_inside -= (Integer) this.inside.get(i).get(0);
                this.inside.remove(i);
            }
        }
    }

    public void setElevatorDestination() {
        if (this.count_inside != 0) {
            this.destination = (Integer) this.inside.get(0).get(1);
        }
    }

    public void isStopElevator() {

        if (this.makeIdle == true) {
            if (this.count_inside == 0) {
                this.mode = "IDLE";
                this.makeIdle = false;
                threads[this.elevatorID].suspend();
            }
        }

    }
}
