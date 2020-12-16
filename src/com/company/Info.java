package com.company;

import java.io.IOException;
import static com.company.Main.*;

public class Info implements Runnable {

    @Override
    public void run() {
        while (true) {
            // katların bilgileri yazdırılır
            printFloors();
            // çıkan kişi sayısı yazdırılır
            System.out.println("exit count: " + exitCount);
            System.out.println();
            // asansörlerin bilgileri yazdırılır
            printElevators();
            System.out.println();
            // her katta kuyrukta bekleyen kişi sayısı yazdırlır
            printPersonNum();
            System.out.println();

//            try {
//                // iplik bekletilir
//                Thread.sleep(0);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                // bilgiler yazdırıldıktan sonra konsol temizlenir
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printFloors() {
        for (int i = 0; i < floors.size(); i++) {
            floors.get(i).printFloor();
        }
    }

    public static void printElevators() {
        for (int i = 0; i < elevators.size(); i++) {
            elevators.get(i).printElevator();
        }
    }

    public static void printPersonNum() {

        for (int i = 0; i < floors.size(); i++) {
            int sum = 0;
            for (int j = 0; j < floors.get(i).waitingAtQueue.size(); j++) {
                sum += Integer.parseInt(String.valueOf(floors.get(i).waitingAtQueue.get(j).get(0)));
            }
            if (i == 0)
                System.out.println(i + ". floor -> queue: " + sum);
            else
                System.out.println(i + ". floor -> all: " + floors.get(i).numOfPeopleOnFloor + " queue: " + sum);
            sum = 0;

        }
    }

}
