package com.company;

import java.util.ArrayList;
import java.util.Random;

import static com.company.Main.*;

/**
 * The Login class simulates the arrival of people at the ground floor.
 * It implements the Runnable interface to be used in a thread.
 */
public class Login implements Runnable {
    public static ArrayList<ArrayList<Integer>> peopleEntering = new ArrayList<>();
    public static int numberOfPeople = 0;

    /**
     * The main run method that is executed when the thread starts.
     * It generates random numbers of people and their destination floors,
     * then adds them to the queue at the ground floor.
     */
    @Override
    public void run() {
        while (true) {
            ArrayList<Integer> tuple = new ArrayList<>();
            tuple.add(getRandomNumber(1, 11)); // Random number of people
            tuple.add(getRandomNumber(1, 5)); // Random destination floor
            numberOfPeople += tuple.get(0);

            peopleEntering.add(tuple);
            floors.get(0).waitingAtQueue.add(tuple);
            floors.get(0).numOfPeopleOnFloor += tuple.get(0);

            Thread control = new Thread(new Control());
            control.start();

            try {
                Thread.sleep(500); // Wait for 500 ms before generating a new group
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates a random number between the specified min (inclusive) and max (exclusive).
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random integer between min and max.
     */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }
}