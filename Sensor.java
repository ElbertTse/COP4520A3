import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Sensor implements Runnable{

    private ConcurrentSkipListSet<Integer>[] temps;
    private AtomicInteger globalClock;
    private int prevTime;

    public Sensor(ConcurrentSkipListSet<Integer>[] temps, AtomicInteger globalClock) {
        this.temps = temps;
        this.globalClock = globalClock;
        this.prevTime = -1;
    }

    @Override
    public void run() {
        
        while (this.globalClock.get() < 1440) {
            
        }
        
    }
    
}
