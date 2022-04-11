import java.lang.Math;

public class TempData implements Comparable<TempData>{

    private class TempTimestamp {
        public int time;
        public double temp;

        public TempTimestamp(int time, double temp) {
            this.time = time;
            this.temp = temp;
        }
    }

    private double maxDifference;
    private TempTimestamp[] interval;
    private int ptr;
    private int start;
    private int end;

    public TempData() {
        this.maxDifference = Double.MIN_VALUE;
        this.interval = new TempTimestamp[10];
        this.ptr = 0;
        this.start = 0;
        this.end = 0;
    }

    public double getMaxDifference() {
        return this.maxDifference;
    }

    public String getTimeDifference() {
        if (this.start < this.end) {
            return "The greatest temperature difference happened between " + this.start + 
                " seconds and " + this.end + " seconds with a difference of " + 
                this.maxDifference + " degrees";
        } else {
            return "The greatest temperature difference happened between " + this.end + 
                " seconds and " + this.start + " seconds with a difference of " + 
                this.maxDifference + " degrees";
        }
    }

    public void add(int time, double temp) {
 
        this.interval[ptr++ % 10] = new TempTimestamp(time, temp);

        if (ptr >= 10) {
            updateDiff();
        }
    }

    public void clear() {
        this.interval = new TempTimestamp[10];
    }

    public void updateDiff() {
        double minVal = Double.MAX_VALUE, maxVal = Double.MIN_VALUE;

        for (int i = 0; i < 10; i++) {
            // Attempt to update the min temp
            minVal = Math.min(minVal, this.interval[i].temp);
            
            // If min temp did change, update the time
            if (minVal == this.interval[i].temp) {
                this.start = this.interval[i].time;
            }

            // Attempt to update the max temp
            maxVal = Math.max(maxVal, this.interval[i].temp);

            // If max temp did change, update the time

            if (maxVal == this.interval[i].temp) {
                this.end = this.interval[i].time;
            }
        }

        this.maxDifference = maxVal - minVal;
    }

    @Override
    public int compareTo(TempData o) {
        
        return Double.compare(this.maxDifference, o.maxDifference);
    }

}
