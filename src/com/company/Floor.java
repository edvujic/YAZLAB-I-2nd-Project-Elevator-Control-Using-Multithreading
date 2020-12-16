package com.company;

import java.util.ArrayList;

public class Floor {
    int floorID;
    int numOfPeopleOnFloor;
    ArrayList <ArrayList> waitingAtQueue;

    public Floor(int floorID, int numOfPeopleOnFloor, ArrayList waitingAtQueue) {
        this.floorID = floorID;
        this.numOfPeopleOnFloor = numOfPeopleOnFloor;
        this.waitingAtQueue = waitingAtQueue;
    }

    public void printFloor()
    {
        System.out.println(this.floorID +". floor :" + this.waitingAtQueue);
    }
}
