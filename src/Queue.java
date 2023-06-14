import java.util.PriorityQueue;

public class Queue{
    private PriorityQueue<Event> events;

    Queue(){
        this.events = new PriorityQueue<Event>();
    }

    public void add(Event event){
        events.add(event);
    }
    public Event next(){
        return events.poll();
    }
    public int size(){
        return events.size();
    }
    public void end(){
        events.clear();
    }     
}
