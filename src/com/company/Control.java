package com.company;

import static com.company.Main.*;

/**
 * Represents a control mechanism for managing elevators in a building.
 */
public class Control implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (newElevatorNeeded()) {
                int placeHolder = 4;
                for (int i = 1; i < elevators.size(); i++) {
                    if (elevators.get(i).mode.equals("IDLE")) {
                        placeHolder = i;
                        break;
                    }
                }
                startNewLift(placeHolder, setDestination());
                break;
            } else {
                for (int i = 1; i < elevators.size(); i++) {
                    if (elevators.get(i).mode.equals("ACTIVE")) {
                        elevators.get(i).makeIdle = true;
                    }
                }
                break;
            }
        }
    }

    /**
     * Checks if a new elevator is needed based on the number of people waiting on each floor.
     * @return True if a new elevator is needed, false otherwise.
     */
    public boolean newElevatorNeeded() {
        boolean toBeReturned = false;
        for (int i = 0; i < floors.size(); i++) {
            int sum = 0;
            for (int j = 0; j < floors.get(i).waitingAtQueue.size(); j++) {
                sum += (Integer) floors.get(i).waitingAtQueue.get(j).get(0);
            }
            if (sum >= 20) {
                toBeReturned = true;
            }
            sum = 0;
        }
        return toBeReturned;
    }

    /**
     * Sets the destination floor for the new elevator based on the number of people waiting on each floor.
     * @return The destination floor number.
     */
    public int setDestination() {
        int destination = 0;

        for (int i = 0; i < floors.size(); i++) {
            int sum = 0;
            for (int j = 0; j < floors.get(i).waitingAtQueue.size(); j++) {
                sum += (Integer) floors.get(i).waitingAtQueue.get(j).get(0);
            }
            if (sum >= 20)
                destination = i;
            sum = 0;
        }
        return destination;
    }
}
