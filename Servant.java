import java.util.concurrent.atomic.AtomicInteger;

public class Servant implements Runnable {

    private AtomicInteger gifts;
    private ConcurrentLL chain;
    private int counter;

    public Servant(AtomicInteger gifts, ConcurrentLL chain) {
        this.gifts = gifts;
        this.chain = chain;
        this.counter = 0;
    }

    @Override
    public void run() {
        int ctr = this.gifts.getAndIncrement();
        try {
            do {
                switch (counter % 3) {
                    case 0:
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

            while (!this.chain.isEmpty()) {
                this.chain.dequeue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
