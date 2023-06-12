import java.util.*;

public class FeromonasCycle{
    private int pathWeight;
    private int graphTotalWeight;
    private ACOAnt sampleAnt;


    FeromonasCycle(int pathWeight, int graphTotalWeight, ACOAnt sampleAnt){
        this.pathWeight = pathWeight;
        this.graphTotalWeight = graphTotalWeight;
        this.sampleAnt = sampleAnt;
    }

    public void updateFeromonas(Constant file, FeromonasGraph graph){
        float feromonasLevel = file.getgamma()*graphTotalWeight/pathWeight;
        List<Integer> test = sampleAnt.getCurrentPath(); 
        for (int i = 0; i < test.size() - 1; i += 2) {
            graph.addEdge(test.get(i),test.get(i+1),feromonasLevel);
        }
    }
}