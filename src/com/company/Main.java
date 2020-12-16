package com.company;

import java.util.ArrayList;

public class Main {

    public static int FLOOR_NUM = 5;
    public static int ELEVATOR_NUM = 5;
    public static ArrayList<Floor> floors = new ArrayList<>();
    public static ArrayList<Elevator> elevators = new ArrayList<>();
    // kesişen şeklinde çalışmasını sağlayan kısım
    public static Thread[] threads = new Thread[5];
    public static int exitCount=0;

    public static void main(String[] args) {

        // katlar oluşturulur
        initializeFloors();
        // asansörler oluşturulur
        initializeElevator();
        // ilplik dizisi oluşturlur
        initializeThreadArray();

        // giriş için kullanılan iplik
        Thread login = new Thread(new Login());
        login.start();

        // bilgileri yazdırmak için iplik oluşturulur
        Thread info = new Thread(new Info());
        info.start();

        // kontrol ipliği başlatılır
        Thread control = new Thread(new Control());
        control.start();

        // çıkış için kullanılan iplik
        Thread exit = new Thread(new Exit());
        exit.start();

        // 0. asansör sürekli çalışacağı için şimdiden başatılır
        threads[0].start();

    }

    public static void initializeFloors() {
        for (int i = 0; i < FLOOR_NUM; i++) {
            floors.add(new Floor(i, 0, new ArrayList()));
        }
    }

    public static void initializeElevator() {
        for (int i = 0; i < ELEVATOR_NUM; i++) {
            elevators.add(new Elevator(i, "IDLE", 0, 0, "up", 10, 0, new ArrayList(), false));
        }
    }

    public static void initializeThreadArray() {
        for (int i = 0; i < ELEVATOR_NUM; i++) {
            threads[i] = new Thread(elevators.get(i));
        }
    }

    public static void startNewLift(int liftCount, int destination) {

        if (String.valueOf(threads[liftCount].getState()).equals("NEW")) {
            elevators.get(liftCount).destination = destination;
            threads[liftCount].start();

        } else {
            elevators.get(liftCount).destination = destination;
            threads[liftCount].resume();
        }


    }


}
