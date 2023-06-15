package pec;

/**
 * The EventObservation class represents an event that performs an observation at a specific time during the simulation.
 */
public class EventObservation extends Event {
    private Observation observationFile;
    private int count;

    /**
     * Constructs an EventObservation object with the specified time, count, and observation file.
     *
     * @param time           The time of the observation event.
     * @param count          The count of the observation.
     * @param observationFile The observation file.
     */
    public EventObservation(double time, int count, Observation observationFile) {
        super(time);
        this.observationFile = observationFile;
        this.count = count;
    }

    /**
     * Runs the observation event, printing the observation details.
     *
     * @param list The event queue.
     */
    @Override
    public void runEvent(Queue list) {
        System.out.println();
        System.out.println("Observation " + this.count);
        System.out.println("Present instant: " + time);
        System.out.println("Number of move events: " + observationFile.getMove());
        System.out.println("Number of evaporation events: " + observationFile.getEvaporation());
        System.out.println("Top candidate cycles:");
        observationFile.printCandidates();
        System.out.println("Best Hamiltonian cycle:");
        observationFile.printBest();
        System.out.println();
    }
}