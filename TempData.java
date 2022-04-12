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
        this.interval = new TempTimestamp[60];
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
                this.maxDifference + " degrees F";
        } else {
            return "The greatest temperature difference happened between " + this.end + 
                " seconds and " + this.start + " seconds with a difference of " + 
                this.maxDifference + " degrees F";
        }
    }

    public void add(int time, double temp) {
        // Add a new timestamp to the list
        this.interval[ptr] = new TempTimestamp(time, temp);

        // After 10 minutes have passed, start looking for an interval
        if (ptr >= 10) {
            updateDiff();
        }
        ptr++;
    }

    public void clear() {
        this.interval = new TempTimestamp[10];
    }

    public void updateDiff() {
        double tempDifference = this.interval[ptr].temp - this.interval[ptr - 10].temp;        
        
        if (tempDifference > this.maxDifference) {
            this.maxDifference = tempDifference;

            this.start = this.interval[ptr].time;
            this.end = this.interval[ptr - 10].time;
        }
    }

    @Override
    public int compareTo(TempData o) {
        return Double.compare(this.maxDifference, o.maxDifference);
    }

}
