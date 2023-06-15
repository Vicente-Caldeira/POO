package pec;
import ACOAnt.*;
import Main.Constant;
import grafos.FeromonasGraph;

/**
 * The EventMove class represents an event for moving an ACOAnt in the graph.
 * It extends the Event class and implements the runEvent() method.
 */
public class EventMove extends Event {
    private ACOAnt ant;
    private FeromonasGraph feromonasGraph;
    private Constant file;
    private Observation fileObservation;

    /**
     * Constructs an EventMove object with the given parameters.
     *
     * @param time            The time of the event.
     * @param ant             The ACOAnt object representing the ant.
     * @param feromonasGraph  The FeromonasGraph object representing the graph.
     * @param file            The Constant object for configuration data.
     * @param fileObservation the observer, responsible for "observing" the events and printing the results
     */
    public EventMove(Double time, ACOAnt ant, FeromonasGraph feromonasGraph, Constant file, Observation fileObservation) {
        super(time);
        this.ant = ant;
        this.feromonasGraph = feromonasGraph;
        this.file = file;
        this.fileObservation = fileObservation;
    }

    /**
     * Runs the move event, executing the ant's move operation, updating the move count in the observation,
     * and scheduling the next move event.
     *
     * @param list The event queue.
     */
    @Override
    public void runEvent(Queue list) {
        double executeTime = ant.move(list, feromonasGraph, file, time);
        fileObservation.updateMove();
        this.nextEvent(new EventMove(this.time + executeTime, ant, feromonasGraph, file, fileObservation), list);
    }
}