public class PresentScenario {
    public static void main(String[] args) {
        ConcurrentLL giftbag = new ConcurrentLL();
        ConcurrentLL chain = new ConcurrentLL();
        Thread[] threads = new Thread[4];

        try {
            System.out.println("Smoke");
            for (int i = 0; i < 10000; i++) {
                System.out.println("Adding " + i);
                giftbag.add(i);
            }
            System.out.println("Fire");
    
            // for (int i = 0; i < 4; i++) {
            //     threads[i] = new Thread(new TaskRunner(giftbag, chain));
            // }
    
            // for (Thread thread : threads) {
            //     thread.start();
            // }
    
            // for (Thread thread : threads) {
            //     thread.join();
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}