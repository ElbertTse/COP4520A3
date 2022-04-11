import java.util.concurrent.ConcurrentSkipListSet;
public class Rover {

    public static void printReport(int day, ConcurrentSkipListSet<Double> temps) {
        double[] highs = new double[5];
        double[] lows = new double[5];

        for (int i = 0; i < 5; i++) {
            highs[i] = temps.pollLast();
            lows[i] = temps.pollFirst();
        }

        System.out.println("Hour " + (day + 1) + " report:\n");

        System.out.println("Top 5 highest temperatures: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(highs[i] + " ");
        }

        System.out.println("\n");

        System.out.println("Top 5 lowest temperatures: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(lows[i] + " ");
        }

        System.out.println("\n\n--------------------------------------------------\n");
    }

    public static void main(String[] args) {
        int numThreads = 8;
        // Add one more thread for control module
        Thread[] threads = new Thread[numThreads];
        ConcurrentSkipListSet<Double> temps = new ConcurrentSkipListSet<>();
        
        System.out.println();

        try {
            // Run for 24 hours
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < numThreads; j++) {
                    threads[j] = new Thread(new Sensor(temps));
                }

                for (Thread thread : threads) {
                    thread.start();
                }
    
                for (Thread thread : threads) {
                    thread.join();
                }
                
                printReport(i, temps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
