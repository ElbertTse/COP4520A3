import java.util.concurrent.atomic.AtomicInteger;

public class PresentScenario {
    public static void main(String[] args) {
        AtomicInteger gifts = new AtomicInteger(0);
        ConcurrentLL chain = new ConcurrentLL();
        Thread[] threads = new Thread[4];

        try {
            for (int i = 0; i < 4; i++) {
                threads[i] = new Thread(new TaskRunner(gifts, chain));
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