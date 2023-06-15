package ACOAnt;
import java.util.*;

import Main.Constant;
import grafos.FeromonasGraph;
import grafos.WeightedGraph;
import pec.EventMove;
import pec.Observation;
import pec.Queue;


/**
 * The Colony class is responsible for creating the ants and adding them to the queue.
 */
public class Colony {
    private List <ACOAnt> ants;
    private FeromonasGraph feromonasGraph;

    /**
     * Constructs a Colony object with the specified feromonasGraph.
     * 
     * @param feromonasGraph the feromonas graph
     */
    public Colony(FeromonasGraph feromonasGraph) {
        this.ants = new ArrayList<>();
        this.feromonasGraph = feromonasGraph;
    }

    /**
     * Creates the ants and adds them to the queue.
     * 
     * @param AntNumber the number of ants
     * @param startNode the nest node
     * @param nodeNumber the number of nodes
     * @param list the queue of events
     * @param graph the weighted graph
     * @param file the constant file
     * @param fileObservation the observer, responsible for "observing" the events and printing the results
     */
    public void createAnt(int AntNumber, int startNode, int nodeNumber, Queue list,WeightedGraph graph, Constant file, Observation fileObservation) {
        for (int i = 0; i < AntNumber; i++) {
            ACOAnt ant = new ACOAnt(nodeNumber, startNode,graph,file,fileObservation);
            ants.add(ant);
            list.add(new EventMove(0.0,ant,feromonasGraph,file,fileObservation));
        }
    }
}