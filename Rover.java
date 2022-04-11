import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
public class Rover {
    public static void main(String[] args) {
        int numThreads = 8;
        // Add one more thread for control module
        Thread[] threads = new Thread[numThreads + 1];
        AtomicInteger clock = new AtomicInteger();
        ArrayList<ConcurrentSkipListSet<Integer>> temps = new ArrayList<>();

        

    }
}
