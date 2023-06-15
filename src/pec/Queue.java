package pec;
import java.util.PriorityQueue;

/**
 * The Queue class represents a queue of events.
 */
public class Queue {
    private PriorityQueue<Event> events;

    /**
     * Constructs a Queue object.
     */
    public Queue() {
        this.events = new PriorityQueue<>();
    }

    /**
     * Adds an event to the queue.
     *
     * @param event The event to be added.
     */
    public void add(Event event) {
        events.add(event);
    }

    /**
     * Retrieves and removes the next event from the queue.
     *
     * @return The next event in the queue.
     */
    public Event next() {
        return events.poll();
    }

    /**
     * Returns the size of the queue.
     *
     * @return The size of the queue.
     */
    public int size() {
        return events.size();
    }

    /**
     * Clears the queue, removing all events.
     */
    public void end() {
        events.clear();
    }
}
