package com.company;

import java.util.ArrayList;

/**
 * The Floor class represents a floor in the building simulation.
 * It holds information about the number of people on the floor and the queue of people waiting.
 */
public class Floor {
    int floorID;
    int numOfPeopleOnFloor;
    ArrayList<ArrayList<Integer>> waitingAtQueue;

    /**
     * Constructs a Floor instance with a specified floor ID, number of people on the floor, and a queue of people waiting.
     *
     * @param floorID The ID of the floor.
     * @param numOfPeopleOnFloor The number of people currently on the floor.
     * @param waitingAtQueue The queue of people waiting on the floor, represented as a list of lists of integers.
     */
    public Floor(int floorID, int numOfPeopleOnFloor, ArrayList<ArrayList<Integer>> waitingAtQueue) {
        this.floorID = floorID;
        this.numOfPeopleOnFloor = numOfPeopleOnFloor;
        this.waitingAtQueue = waitingAtQueue;
    }

    /**
     * Prints information about the floor, including the floor ID and the list of people waiting in the queue.
     */
    public void printFloor() {
        System.out.println(this.floorID + ". floor: " + this.waitingAtQueue);
    }
}