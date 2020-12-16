package com.company;

import static com.company.Main.*;

public class Control implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (newElevatorNeeded()) { // true dönerse o zaman yeni bir asansör talep edilir
                int placeHolder = 4;
                for (int i = 1; i < elevators.size(); i++) {
                    if (elevators.get(i).mode.equals("IDLE")) { // "idle" durumunda olan asansör aranır
                        placeHolder = i; // "idle"i olan asansörün kimliği tutulur
                        break;
                    }
                }
                startNewLift(placeHolder, setDestination()); // yeni bir asansör çalıştırılır ve istikameti belirlenir
                break;
                // tüm katlardaki kişi sayısı 20'nin altında ise
            } else {
                for (int i = 1; i < elevators.size(); i++) {
                    // "active" durumunda olan asansör aranır
                    if (elevators.get(i).mode.equals("ACTIVE")) {
                        // asansör, müşterileri indirene kadar çalışır
                        elevators.get(i).makeIdle = true;
                        // indirdikten sonra durumu "idle" olur
                    }
                }
                break;
            }
        }
    }

    public boolean newElevatorNeeded() {
        // döndürecek değer
        boolean toBeReturned = false;
        for (int i = 0; i < floors.size(); i++) {
            // kuyrukta bekleyen kişi sayısını hesaplaman için kullanılır
            int sum = 0;
            for (int j = 0; j < floors.get(i).waitingAtQueue.size(); j++) {
                // kuyrukta bekleyen kişiler toplanır
                sum += (Integer) floors.get(i).waitingAtQueue.get(j).get(0);
            }
            // eğer herhangi bir kuyrukta kişi sayısı 20'den fazla ise o zaman döndürecek değer true yapılır
            if (sum >= 20) {
                toBeReturned = true;
            }
            sum = 0;
        }
        return toBeReturned;
    }

    public int setDestination() {
        int destination = 0;  // döndürecek değeir

        for (int i = 0; i < floors.size(); i++) {
            int sum = 0;
            for (int j = 0; j < floors.get(i).waitingAtQueue.size(); j++) {
                sum += (Integer) floors.get(i).waitingAtQueue.get(j).get(0);
            }
            if (sum >= 20)
                destination = i; // 20 kişiyi aşan kat numarası
            sum = 0;
        }
        return destination;
    }
}
