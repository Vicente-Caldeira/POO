public class EventEnd extends Event{
    private Observation endObservation;
    private int lastCount;

    EventEnd(double time,Observation endObservation, int lastCount){
        super(time);
        this.endObservation = endObservation;
        this.lastCount = lastCount;
    }
    @Override
    public void runEvent(Queue list){
        System.out.println("Observation " + this.lastCount);
        System.out.println("Present instant: " + time);
        System.out.println("Number of move events: " + endObservation.getMove());
        System.out.println("Number of evaporation events: " + endObservation.getEvaporation());
        System.out.println("\tTop candidate cycles:");
        endObservation.printCandidates();
        System.out.println("Best Hamiltonian cycle:");
        endObservation.printBest();
        list.end();
    }

    
}