// Elbert Tse, COP 4520, Spring 2022

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

public class Sensor implements Runnable{

    private ConcurrentSkipListSet<Double> temps;

    public Sensor(ConcurrentSkipListSet<Double> temps) {
        this.temps = temps;
    }

    @Override
    public void run() {
        Random random = new Random();
        
        for (int i = 0; i < 60; i++) {
            // get a random number from 0 to 170, add it to the min. temp
            double temp = random.nextDouble(170) - 100;
            temps.add(temp);
        }
    }
    
}
