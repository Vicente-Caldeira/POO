import java.util.*;

interface ant {
    void move (WeightedGraph graph);
    int findPath (WeightedGraph graph);
}


class ACOAnt implements ant{

    private int currentNode;
    private Set<Integer> visitedNodes;
    private List<Integer> currentPath;
    private int startNode;

    ACOAnt(int n, int initialNode) {
        this.visitedNodes = new HashSet<>(n);
        this.currentPath = new ArrayList<>(n);
        this.currentNode = initialNode;
        this.visitedNodes.add(initialNode);
        this.currentPath.add(initialNode);
        this.startNode = initialNode;
    }
    public List<Integer> getCurrentPath(){
        return this.currentPath;
    }
    public void move(WeightedGraph graph) {
        //findPath(graph);

        while (true) {
            int nextNode = choosePath(graph);
            if (visitedNodes.size() < graph.getNodeNumber()) {
                visitedNodes.add(nextNode);
                currentPath.add(nextNode);
                currentNode = nextNode;
            }
            else {
                System.out.println("No possible nodes");
                currentPath.add(startNode);
                System.out.println("Current Path: " + currentPath);
            return;
            }
        }
        

    }
    public int findPath(WeightedGraph graph) {

        List<Integer> possibleNodes = new ArrayList<>();

        for (int i = 0; i < graph.getNodeNumber(); i++) {
            if (graph.getWeight(currentNode, i) != 0)
                possibleNodes.add(i);
        }

        int totalOptions=possibleNodes.size();

        int nextNode = possibleNodes.get((int) (Math.random() * totalOptions));

        update_vectors(nextNode);

        return nextNode;
    }

    private void update_vectors(int nextNode) {


        System.out.println("Current Path start update: " + currentPath);

        // Find the position (index) where the given number would be located in the first array
        for (int i = 0; i < currentPath.size(); i++) {
            if (currentPath.get(i) >= nextNode) {
                int size = currentPath.size();
                currentPath.subList(i, size).clear();
                break;
            }
        }

        System.out.println("Current Path after loop update: " + currentPath);

        System.out.println("Visited Nodes before: " + visitedNodes);

        // erase the path from the index to the end
        Iterator<Integer> iterator = visitedNodes.iterator();
        while (iterator.hasNext()) {
            int currentValue = iterator.next();
            if (currentValue > nextNode) {
                iterator.remove();
            }
        }

        System.out.println("Visited Nodes after: " + visitedNodes);
        
        System.out.println("Current Path end update: " + currentPath);


        return;

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

        System.out.println("Possible Nodes size: " + possibleNodes.size());

        //all adjacent nodes are visited

        if(possibleNodes.size() == 0 && visitedNodes.size() == graph.getNodeNumber()) {
            System.out.println("All nodes visited");
            int nextNode = startNode;
            return nextNode;
        }

        System.out.println("Current path atual 32434: " + currentPath);


        if (possibleNodes.size() == 0) {
            System.out.println("No possible nodes222222222222");
            int nextNode = findPath(graph);
            return nextNode;
        }

        int totalOptions=possibleNodes.size();

        int nextNode = possibleNodes.get((int) (Math.random() * totalOptions)); //TODO: Add the pheromones algorythm

        System.out.println("Next Node: " + nextNode);
        System.out.println("Current path atual: " + currentPath);

        return nextNode;
    }
}

public class Colony {
    private List <ACOAnt> ants;

    Colony() {
        this.ants = new ArrayList<>();
    }

    public void createAnt(int AntNumber, int ColonyNode, WeightedGraph graph, Constant projConstant) {
        for (int i = 0; i < AntNumber; i++) {
            ACOAnt ant = new ACOAnt(graph.getNodeNumber(), (int)(Math.random()* projConstant.getNodeInit()));
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

