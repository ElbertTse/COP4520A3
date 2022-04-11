// Elbert Tse, COP 4520, Spring 2022

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

public class Sensor implements Runnable{

    private ConcurrentSkipListSet<Double> temps;
    private ConcurrentSkipListSet<TempData> data;

    public Sensor(ConcurrentSkipListSet<Double> temps, ConcurrentSkipListSet<TempData> data) {
        this.temps = temps;
        this.data = data;
    }

    @Override
    public void run() {
        Random random = new Random();
        TempData tempData = new TempData();

        for (int i = 0; i < 60; i++) {
            // get a random number from 0 to 170, add it to the min. temp
            double temp = random.nextDouble(170) - 100;
            this.temps.add(temp);
            tempData.add(i, temp);
        }
        
        this.data.add(tempData);
    }
    
}
