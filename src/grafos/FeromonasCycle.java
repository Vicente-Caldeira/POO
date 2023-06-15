package grafos;
import pec.EventEvaporate;
import java.util.*;
import Main.Constant;
import pec.Observation;
import pec.Queue;


/**
 * The FeromonasCycle class is responsible for  calculating the concentration 
 * of pheromones and places them on the edges traveled by the ant. 
 * If the edge has no pheromones, the evaporation event is called. Otherwise, it just updates the value.
 */
public class FeromonasCycle{
    private int pathWeight;
    private int graphTotalWeight;
    private FeromonasGraph graph;


    /**
     * Constructs a FeromonasCycle object with the specified pathWeight, graphTotalWeight and graph.
     * @param pathWeight the weight of the path taken by the ant
     * @param graphTotalWeight the total weight of the graph
     * @param graph the feromonas graph
     */
    public FeromonasCycle(int pathWeight, int graphTotalWeight,FeromonasGraph graph){
        this.pathWeight = pathWeight;
        this.graphTotalWeight = graphTotalWeight;
        this.graph = graph;
    }


    /**
     * Calculates the concentration of pheromones and places them on the edges traveled by the ant. 
     * If the edge has no pheromones, the evaporation event is called. Otherwise, it just updates the value.
     * @param list the queue of events
     * @param file the constant file
     * @param test the path taken by the ant
     * @param time the time of the event
     * @param observer the observer, responsible for "observing" the events and printing the results
     */
    public void updateFeromonas(Queue list, Constant file, List<Integer> test, double time, Observation observer){
        float feromonasLevel = file.getgamma()*graphTotalWeight/pathWeight;
        for (int i = 0; i < test.size() - 1; i ++) {
            float feromonas = 0;
            feromonas = feromonasLevel + graph.getWeight(test.get(i),test.get(i+1));

            if(graph.getWeight(test.get(i),test.get(i+1)) == 0){
                list.add(new EventEvaporate(time,test.get(i),test.get(i+1), file.geteta(),file.getrho(), graph,observer));
            }
            graph.addEdge(test.get(i),test.get(i+1),feromonas);


        }
    }
}