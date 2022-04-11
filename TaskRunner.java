import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable {

    private AtomicInteger gifts;
    private ConcurrentLL chain;
    private int counter;

    public TaskRunner(AtomicInteger gifts, ConcurrentLL chain) {
        this.gifts = gifts;
        this.chain = chain;
        this.counter = 0;
    }

    @Override
    public void run() {
        int ctr = this.gifts.getAndIncrement();
        boolean writing = false;
        try {
            do {
                switch (counter % 3) {
                    case 0:
                        // System.out.println(Thread.currentThread().getName() + " adding " + ctr + " with chain: " + this.chain);
                        this.chain.add(ctr);
                        break;
                    
                    case 1:
                        this.chain.dequeue();
                        break;

                    case 2:
                        this.chain.contains(counter);
                        break;
                }

                this.counter++;
                ctr = this.gifts.getAndIncrement();
            } while (ctr < 500000);

            writing = true;

            while (!this.chain.isEmpty()) {
                this.chain.dequeue();
            }

        } catch (Exception e) {
            // System.out.println(Thread.currentThread().getName() + " ctr " + ctr + ", op " + counter % 3 + ", writing? " + writing + ", chain: " + chain);
            // e.printStackTrace();
        }
    }
    
}
