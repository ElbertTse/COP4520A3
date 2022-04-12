// Elbert Tse, COP 4520, Spring 2022

import java.util.concurrent.ConcurrentSkipListSet;

public class Rover {

    public static void printReport(int hour, ConcurrentSkipListSet<Double> temps, ConcurrentSkipListSet<TempData> diffs) {
        System.out.println("Hour " + (hour + 1) + " report:\n");

        System.out.println("Top 5 highest temperatures (Degrees F): ");
        for (int i = 0; i < 5; i++) {
            System.out.print(temps.pollLast() + " ");
        }

        System.out.println("\n");

        System.out.println("Top 5 lowest temperatures (Degrees F): ");
        for (int i = 0; i < 5; i++) {
            System.out.print(temps.pollFirst() + " ");
        }

        System.out.println("\n\n" + diffs.pollLast().getTimeDifference());
        System.out.println("\n--------------------------------------------------\n");
    }

    public static void main(String[] args) {
        int numThreads = 8;
        Thread[] threads = new Thread[numThreads];
        ConcurrentSkipListSet<Double> temps = new ConcurrentSkipListSet<>();
        ConcurrentSkipListSet<TempData> diffs = new ConcurrentSkipListSet<>();
        
        System.out.println();

        try {
            // Run for 24 hours
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < numThreads; j++) {
                    threads[j] = new Thread(new Sensor(temps, diffs));
                }

                for (Thread thread : threads) {
                    thread.start();
                }
    
                for (Thread thread : threads) {
                    thread.join();
                }
                
                printReport(i, temps, diffs);
                temps.clear();
                diffs.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
