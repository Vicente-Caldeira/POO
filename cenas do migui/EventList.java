package pec;

import java.util.PriorityQueue;

public class EventList {
    // the priority queue
    private final PriorityQueue<Event> events;

    // constructor
    public EventList() {
        events = new PriorityQueue<Event>(Event::compareTo);
    }

    // add an event to the list
    public void add(Event _event) {
        events.add(_event);
    }

    // get the next event
    public IEvent next() {
        return events.poll();
    }

    // get the size of the list
    public int size() {
        return events.size();
    }

    public void clear() {
        events.clear();
    }

    // remove first
    public Event removeFirst() {
        return events.poll();
    }
}
