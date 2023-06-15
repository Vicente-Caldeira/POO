package pec;

/**
 * The EventEnd class represents an event that marks the end of the simulation.
 */
public class EventEnd extends Event {
    private Observation endObservation;
    private int lastCount;

    /**
     * Constructs an EventEnd object with the specified time, end observation, and last count.
     *
     * @param time           The time of the end event.
     * @param endObservation The end observation.
     * @param lastCount      The last count of the observation.
     */
    public EventEnd(double time, Observation endObservation, int lastCount) {
        super(time);
        this.endObservation = endObservation;
        this.lastCount = lastCount;
    }

    /**
     * Runs the end event, printing the final observation details and ending the event queue.
     *
     * @param list The event queue.
     */
    @Override
    public void runEvent(Queue list) {
        System.out.println();
        System.out.println("Observation " + this.lastCount);
        System.out.println("Present instant: " + time);
        System.out.println("Number of move events: " + endObservation.getMove());
        System.out.println("Number of evaporation events: " + endObservation.getEvaporation());
        System.out.println("Top candidate cycles:");
        endObservation.printCandidates();
        System.out.println("Best Hamiltonian cycle:");
        endObservation.printBest();
        list.end();
        System.out.println();
    }
}
