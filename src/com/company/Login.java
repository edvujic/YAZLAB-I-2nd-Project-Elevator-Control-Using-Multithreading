package com.company;

import java.util.ArrayList;
import java.util.Random;

import static com.company.Main.*;

public class Login implements Runnable {
    public static ArrayList peopleEntering = new ArrayList();
    public static int numberOfPeople = 0;

    @Override
    public void run() {
        while (true) {

            // kişi sayısı, gideceği kat "tuple" olarak yapılır
            ArrayList<Integer> tuple = new ArrayList<Integer>();
            // 1-10 rastgele sayı üretilir, zemin katta giren kişi sayısı için
            tuple.add(getRandomNumber(1, 11));
            // 1-4 rastgele sayı üretilir, gideceği kat için
            tuple.add(getRandomNumber(1, 5));
            numberOfPeople += tuple.get(0);

            peopleEntering.add(tuple);
            // zemin kat kuyruğuna üretilen rastgele "tuple" alınır
            floors.get(0).waitingAtQueue.add(tuple);
            // zemin katta kişi sayısı güncellenir
            floors.get(0).numOfPeopleOnFloor += tuple.get(0);

            // kontrol ipliği çalıştırılır
            Thread control = new Thread(new Control());
            control.start();


            try {
                // 500 ms vuruşuyla yeni bir grup oluşturulur
                Thread.sleep(500);
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
