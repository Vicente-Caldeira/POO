import java.util.*;

interface ant {
    void move (WeightedGraph graph);
    void findPath (WeightedGraph graph);
}


class ACOAnt implements ant{

    private int currentNode;
    private Set<Integer> visitedNodes;
    private List<Integer> currentPath;

    ACOAnt(int n, int initialNode) {
        this.visitedNodes = new HashSet<>(n);
        this.currentPath = new ArrayList<>(n);
        this.currentNode = initialNode;
        this.visitedNodes.add(initialNode);
        this.currentPath.add(initialNode);
    }

    public void move(WeightedGraph graph) {
        //findPath(graph);

        while (true) {
            int nextNode = choosePath(graph);
            if (nextNode != -1) {
                visitedNodes.add(nextNode);
                currentPath.add(nextNode);
                currentNode = nextNode;
            }
            else {
                System.out.println("No possible nodes");
                System.out.println("Current Path: " + currentPath);
            return;
            }
        }
        

    }
    public void findPath(WeightedGraph graph) {
        //TODO
    }

    private int choosePath(WeightedGraph graph) {
        //TODO
        List<Integer> possibleNodes = new ArrayList<>();

        for (int i = 0; i < graph.getNodeNumber(); i++) {
            if (graph.getWeight(currentNode, i) != 0)
                if (!visitedNodes.contains(i)) {
                    possibleNodes.add(i);
                }
        }

        System.out.println("Possible Nodes: " + possibleNodes);

        if (possibleNodes.size() == 0) {
            System.out.println("No possible nodes");
            return -1;
        }

        int totalOptions=possibleNodes.size();

        int nextNode = possibleNodes.get((int) (Math.random() * totalOptions)); //TODO: Add the pheromones algorythm

        System.out.println("Next Node: " + nextNode);

        return nextNode;
    }
}

public class Colony {
    private List <ACOAnt> ants;

    Colony() {
        this.ants = new ArrayList<>();
    }

    public void createAnt(int AntNumber, int ColonyNode) {
        for (int i = 0; i < AntNumber; i++) {
            ACOAnt ant = new ACOAnt(n);
            ants.add(ant);
        }
    }

    public void moveAnts(WeightedGraph graph) {
        for (ACOAnt ant : ants) {
            ant.move(graph);
        }
    }

    public void findPath(WeightedGraph graph) {
        for (ACOAnt ant : ants) {
            ant.findPath(graph);
        }
    }
 

}

