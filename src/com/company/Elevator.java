package com.company;

import java.util.ArrayList;

import static com.company.Main.*;

public class Elevator implements Runnable {

    int elevatorID;
    String mode;
    int floor;
    int destination;
    String direction;
    int capacity;
    int count_inside;
    ArrayList<ArrayList> inside;
    boolean isFull;
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
            // asansör müsterileri indirdiyse duracak
            isStopElevator();
            if (elevators.get(0).inside.size() == 0) {
                // 0. asansör sürekli çalışacağı için sürekli istikameti zemin kata
                elevators.get(0).destination = 0;
            }
            if (this.floor < this.destination) {
                // eğer asansörün bulunduğu katın gideceği kattan daha küçük ise yukarıya gider
                this.goingUp();
                // eğer asansörün bulunduğu katın gideceğin katla aynıysa
            } else if (this.floor == this.destination) {
                // müsteriler indirilir
                this.leaveCustomers();
                // o katta müsteri varsa, asansöre alınır
                this.takeCustomers();
                // asansörün istikameti belirlenir
                this.setElevatorDestination();
            } else {
                // eğer asansörün bulunduğu katın gideceği kattan daha büyük ise aşağıya gider
                this.goingDown();
            }
            try {
                Thread.sleep(200); // bir kat arasındaki geçiş süresi
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
        // her kattaki kişi sayısı için
        for (int i = 0; i < floors.get(this.floor).waitingAtQueue.size(); i++) {
            // eğer asansörün içindeki kişi sayısı ve bulunduğu kattaki müsterilerin sayısı kapasitesinden küçükse müsteriler asansöre alınır
            if (this.count_inside + (Integer) floors.get(this.floor).waitingAtQueue.get(i).get(0) <= this.capacity) {
                // kapasitesini aşmayacak kadar müsteri asansöre alınır
                this.inside.add(floors.get(this.floor).waitingAtQueue.get(i));
                // asansördeki bulunan kişi sayısı güncellenir
                this.count_inside += (Integer) floors.get(this.floor).waitingAtQueue.get(i).get(0);
                // katta kişi sayısı güncellenir
                floors.get(this.floor).numOfPeopleOnFloor -= (Integer) floors.get(this.floor).waitingAtQueue.get(i).get(0);
                // asansöre binen kişiler kuyruktan silinir
                floors.get(this.floor).waitingAtQueue.remove(i);
            }

        }
    }

    public void leaveCustomers() {
        // asansörün içindeki her müşteri için
        for (int i = 0; i < this.inside.size(); i++) {
            // eğer asansördeki müsteri bu katta inmek istiyorsa
            if ((Integer) this.inside.get(i).get(1) == this.floor) {
                // ve inmesi gereken kat zemin kat değilse
                if ((Integer) this.inside.get(i).get(1) != 0) {
                    // kattaki müsteri sayısı güncellenir
                    floors.get(this.floor).numOfPeopleOnFloor += (Integer) this.inside.get(i).get(0);
                } else {
                    //eğer ineceği kat 0 ise o zaman çıkış sayısı güncellenir
                    exitCount += (Integer) this.inside.get(i).get(0);
                }
                // asansörün içindeki kişi sayısı güncellenir
                if(this.count_inside>0)
                    this.count_inside -= (Integer) this.inside.get(i).get(0);
                // çıkan kişiler asansörden çıkartırılır
                this.inside.remove(i);
            }
        }

    }

    public void setElevatorDestination() {
        // eğer asansördeki kiş sayısı 0 değilse
        if (this.count_inside != 0) {
            // ilk giren kişinin gideceği yeri belirle ve asansörü oraya götür
            this.destination = (Integer) this.inside.get(0).get(1);
        }
    }

    public void isStopElevator() {

        // eğer asansörün durması gerekiyorsa
        if (this.makeIdle == true) {
            // asansör tüm müsterileri bindirene kadar çalışır
            if (this.count_inside == 0) {
                // asansörün modu "idle" yapılır
                this.mode = "IDLE";
                // asansör durdurulur
                this.makeIdle = false;
                threads[this.elevatorID].suspend();
            }
        }

    }


}
