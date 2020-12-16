package com.company;

import java.util.ArrayList;
import java.util.Random;
import static com.company.Main.*;

public class Exit implements Runnable {

    @Override
    public void run() {
        while (true) {
            // rastgele üretilecek sayının üst limiti
            int upperBound;
            ArrayList<Integer> tuple = new ArrayList<>();
            // geçici bir değişken
            int floorPHolder;
            while (true) {
                // rastgele bir kat alınır
                int temp = getRandomNumber(1, 5);
                // eğer o kattaki kişi sayısı 0 değilse
                if (floors.get(temp).numOfPeopleOnFloor != 0) {
                    floorPHolder = temp;
                    break;
                }
            }
            if (floors.get(floorPHolder).numOfPeopleOnFloor > 5) {
                // eğer 5'ten büyükse o zaman rastgele üretilecek kişi sayısı
                upperBound = 5;
            } else {
                // eğer 5'ten küçükse onu üst limit olarak alır
                upperBound = floors.get(floorPHolder).numOfPeopleOnFloor;
            }
            // rastgele üretilir
            tuple.add(getRandomNumber(1, upperBound + 1));
            // çıkış olacağı için 0. kata gidilir
            tuple.add(0);

            floors.get(floorPHolder).waitingAtQueue.add(tuple);

            // iplik bekletilir
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // min sınırı dahil, max sınır dahil değil
    public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();

    }
}
