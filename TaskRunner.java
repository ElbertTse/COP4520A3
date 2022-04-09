public class TaskRunner implements Runnable {

    private ConcurrentLL giftbag;
    private ConcurrentLL chain;
    private int counter;

    public TaskRunner(ConcurrentLL giftbag, ConcurrentLL chain) {
        this.giftbag = giftbag;
        this.chain = chain;
        this.counter = 0;
    }

    @Override
    public void run() {
        while (!this.giftbag.isEmpty()) {
            switch (counter % 3) {
                case 0:
                    int gift = this.giftbag.dequeue();
                    this.chain.add(gift);
                    break;
                
                case 1:
                    System.out.println(Thread.currentThread().getName() + " has counter " + counter);
                    break;

                case 2:
                    this.chain.contains(counter);
                    break;
            }

            counter++;
        }
    }
    
}
