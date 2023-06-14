import java.util.*;

public class FeromonasCycle{
    private int pathWeight;
    private int graphTotalWeight;
    private FeromonasGraph graph;

    FeromonasCycle(int pathWeight, int graphTotalWeight,FeromonasGraph graph){
        this.pathWeight = pathWeight;
        this.graphTotalWeight = graphTotalWeight;
        this.graph = graph;
    }

    public void updateFeromonas(Queue list, Constant file,List<Integer> test, double time, Observation observer){
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