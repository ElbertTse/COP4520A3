import java.util.concurrent.atomic.AtomicInteger;

public class PresentScenario {
    public static void main(String[] args) {
        int numThreads = 4;
        AtomicInteger gifts = new AtomicInteger(0);
        ConcurrentLL chain = new ConcurrentLL();
        Thread[] threads = new Thread[numThreads];

        try {
            for (int i = 0; i < numThreads; i++) {
                threads[i] = new Thread(new Servant(gifts, chain));
            }
    
            for (Thread thread : threads) {
                thread.start();
            }
    
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}