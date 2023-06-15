package pec;


/**
 * The Event class represents an abstract event in a priority event queue.
 * It implements the Comparable interface to allow comparison based on event time.
 */
public abstract class Event implements Comparable<Event> {
    double time;

    /**
     * Constructs an Event object with the given time.
     *
     * @param time The time of the event.
     */
    public Event(double time) {
        this.time = time;
    }

    /**
     * Gets the time of the event.
     *
     * @return The time of the event.
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Sets the time of the event.
     *
     * @param time The time to set for the event.
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Runs the event on the given event queue.
     *
     * @param list The event queue to run the event on.
     */
    public void runEvent(Queue list) {}

    /**
     * Adds the next event to the event queue.
     *
     * @param event The next event to add.
     * @param list  The event queue to add the event to.
     */
    public void nextEvent(Event event, Queue list) {
        list.add(event);
    }

    /**
     * Compares this event with the specified event based on their times.
     *
     * @param other The event to compare with.
     * @return A negative integer, zero, or a positive integer if this event is less than,
     *         equal to, or greater than the specified event.
     */
    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}