package com.company;

import java.util.ArrayList;
import java.util.Random;
import static com.company.Main.*;

/**
 * The Exit class implements the Runnable interface and simulates people leaving the building.
 */
public class Exit implements Runnable {

    /**
     * Continuously simulates people exiting from a randomly selected floor.
     */
    @Override
    public void run() {
        while (true) {
            int upperBound;
            ArrayList<Integer> tuple = new ArrayList<>();
            int floorPHolder;
            while (true) {
                int temp = getRandomNumber(1, 5);
                if (floors.get(temp).numOfPeopleOnFloor != 0) {
                    floorPHolder = temp;
                    break;
                }
            }
            if (floors.get(floorPHolder).numOfPeopleOnFloor > 5) {
                upperBound = 5;
            } else {
                upperBound = floors.get(floorPHolder).numOfPeopleOnFloor;
            }
            tuple.add(getRandomNumber(1, upperBound + 1));
            tuple.add(0); // Exit is always to the ground floor, which is 0.

            floors.get(floorPHolder).waitingAtQueue.add(tuple);

            try {
                Thread.sleep(1000); // Sleep for 1 second before simulating the next exit.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates a random number within the specified range [min, max).
     *
     * @param min The inclusive lower bound of the random number range.
     * @param max The exclusive upper bound of the random number range.
     * @return A random integer between min (inclusive) and max (exclusive).
     */
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }
}