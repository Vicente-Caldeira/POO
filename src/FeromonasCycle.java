import java.util.*;

public class FeromonasCycle{
    private int pathWeight;
    private int graphTotalWeight;

    FeromonasCycle(int pathWeight, int graphTotalWeight){
        this.pathWeight = pathWeight;
        this.graphTotalWeight = graphTotalWeight;
    }

    public void updateFeromonas(Constant file, FeromonasGraph graph,List<Integer> test){
        float feromonasLevel = file.getgamma()*graphTotalWeight/pathWeight;;
        for (int i = 0; i < test.size() - 1; i ++) {
            float feromonas = 0;
            feromonas = feromonasLevel + graph.getWeight(test.get(i),test.get(i+1));
            graph.addEdge(test.get(i),test.get(i+1),feromonas);
        }
    }
}