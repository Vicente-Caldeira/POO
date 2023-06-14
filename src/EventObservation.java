public class EventObservation extends Event{
    private Observation observationFile;
    private int count;

    EventObservation(double time, int count, Observation observationFile){
        super(time);
        this.observationFile = observationFile;
        this.count = count;
    }
    @Override
    public void runEvent(Queue list){
        System.out.println("Observation " + this.count);
        System.out.println("Present instant: " + time);
        System.out.println("Number of move events: " + observationFile.getMove());
        System.out.println("Number of evaporation events: " + observationFile.getEvaporation());
        System.out.println("\tTop candidate cycles:");
        observationFile.printCandidates();
        System.out.println("Best Hamiltonian cycle:");
        observationFile.printBest();
    }
}
