package pec;
import java.util.Random;

import grafos.FeromonasGraph;

/**
 * The EventEvaporate class represents an event for evaporating pheromones between two nodes in a graph.
 * It extends the Event class and implements the runEvent() method.
 */
public class EventEvaporate extends Event {
    private int Node1;
    private int Node2;
    private double eta;
    private double rho;
    private FeromonasGraph feromonasgraph;
    private Observation observer;

    /**
     * Constructs an EventEvaporate object with the given parameters.
     *
     * @param time           The time of the event.
     * @param Node1          The first node of the edge to evaporate pheromones.
     * @param Node2          The second node of the edge to evaporate pheromones.
     * @param eta            The mean value for the exponential distribution of time between evaporations.
     * @param rho            The pheromone evaporation rate.
     * @param feromonasgraph The FeromonasGraph object representing the graph.
     * @param observer       the observer, responsible for "observing" the events and printing the results
     */
    public EventEvaporate(double time, int Node1, int Node2, double eta, double rho, FeromonasGraph feromonasgraph, Observation observer) {
        super(time);
        this.Node1 = Node1;
        this.Node2 = Node2;
        this.eta = eta;
        this.rho = rho;
        this.feromonasgraph = feromonasgraph;
        this.observer = observer;
    }

    /**
     * Schedules the next evaporate event and adds it to the event queue.
     *
     * @param list The event queue to add the next event to.
     * @param next The next evaporate event to schedule.
     */
    private void schedule_next(Queue list, EventEvaporate next) {
        list.add(next);
    }

    /**
     * Runs the evaporate event.
     *
     * @param list The event queue.
     */
    @Override
    public void runEvent(Queue list) {
        Random random = new Random();
        double randomValue = -(1 / eta) * Math.log(1 - random.nextDouble());

        float feromonas = feromonasgraph.getWeight(Node1, Node2);
        feromonas -= rho;
        feromonasgraph.addEdge(Node1, Node2, feromonas);

        observer.updateEvaporation();

        schedule_next(list, new EventEvaporate(time + randomValue, Node1, Node2, eta, rho, feromonasgraph, observer));
    }
}