abstract class Event implements Comparable<Event>{
    double time;

    public Event(double time) {
        this.time = time;
    }
    public double getTime(){
        return this.time;
    }
    public void setTime(double time){
        this.time = time;
    }
    public void runEvent(Queue list){}
    public void nextEvent(Event event, Queue list){
        list.add(event);
    }
    
    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}