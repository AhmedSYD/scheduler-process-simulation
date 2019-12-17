
package scheduler_process;


public class Process {
    private String name;
    private float burst;
    private float tempBurst;    //temporay burst and its copy of burst
    private float arrival;
    private int priority;
    private float endTime;//the time that process finish and terminate from cpu

    public Process() {
        name=null; burst=0; arrival=0; priority=0; tempBurst=0;
    }

    public Process(String name, float burst, float arrival) {
        this.name = name;
        this.burst = burst;
        this.tempBurst= burst;
        this.arrival = arrival;
    }
    public Process(String name, float burst, float arrival,int priority) {
        this.name = name;
        this.burst = burst;
        this.tempBurst=burst;
        this.arrival = arrival;
        this.priority=priority;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBurst() {
        return burst;
    }

    public void setBurst(float burst) {
        this.burst = burst;
    }

    public float getArrival() {
        return arrival;
    }

    public void setArrival(float arrival) {
        this.arrival = arrival;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

    public float getTempBurst() {
        return tempBurst;
    }

    public void setTempBurst(float tempBurst) {
        this.tempBurst = tempBurst;
    }
    
    
    
    
    
}
