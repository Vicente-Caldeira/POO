import java.util.*;

interface ant {
    void move (WeightedGraph graph,FeromonasGraph feromonas, Constant file);
    int findPath (WeightedGraph graph);
}


class ACOAnt implements ant{

    private int currentNode;
    private Set<Integer> visitedNodes;
    private List<Integer> currentPath;
    private int startNode;

    ACOAnt(int n, int initialNode){

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
    public void move(WeightedGraph graph,FeromonasGraph feromonas, Constant file) {
        //findPath(graph);

        while (true) {
            int nextNode = choosePath(graph,file,feromonas);
            if (visitedNodes.size() < graph.getNodeNumber()) {
                visitedNodes.add(nextNode);
                currentPath.add(nextNode);
                currentNode = nextNode;
            }
            else {
                System.out.println("No possible nodes");
                currentPath.add(startNode);
                System.out.println("Current Path: " + currentPath);

                int count = 0;
                for(int i = 0; i < this.currentPath.size() - 1; i++){
                    System.out.println("I: " + this.currentPath.get(i) + " J: " + this.currentPath.get(i + 1) + " Weight: "+ graph.getWeight(this.currentPath.get(i),this.currentPath.get(i + 1)));
                    count += graph.getWeight(this.currentPath.get(i),this.currentPath.get(i + 1));
                }
                System.out.println("Path weight:" + count);
                FeromonasCycle updateFeromonas = new FeromonasCycle(graph.totalWeightGraph(), count);
                updateFeromonas.updateFeromonas(file,feromonas, this.currentPath);

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

        // Find the position (index) where the given number would be located in the first array
        for (int i = 0; i < currentPath.size(); i++) {
            if (currentPath.get(i) == nextNode) {
                int size = currentPath.size();
                List<Integer> subList = currentPath.subList(i, size);
                visitedNodes.removeAll(subList);
                subList.clear();
                break;
            }
        }        


        return;

    }
    private int chooseNextNode(WeightedGraph graph, Constant file, FeromonasGraph feromonas, List<Integer> possibleNodes){
        int nextNode = 0;
        int totalOptions=possibleNodes.size();

        List<Float> cijk = new ArrayList<>();

        float ci=0;
        for(int i=0; i<totalOptions; i++){
            float weight=graph.getWeight(currentNode,possibleNodes.get(i));
            float cij=(file.getaplha()+feromonas.getWeight(currentNode,possibleNodes.get(i)))/(file.getbeta()+weight);
            cijk.add(cij);
            ci+=cij;
        }
        
        List<Float> probabilities = new ArrayList<>();
        for(int i=0; i<totalOptions; i++){
            float probability=cijk.get(i)/ci;
            probabilities.add(probability);
        }

        List<Float> cumulativeProbabilities = new ArrayList<>();
        for(int i=0; i<totalOptions; i++){
            float cumulativeProbability=0;
            for(int j=i; j<totalOptions; j++){
                cumulativeProbability+=probabilities.get(j);
            }
            cumulativeProbabilities.add(cumulativeProbability);
        }

        System.out.println("Cumulative Probabilities: " + cumulativeProbabilities);

        float choice = ((float) (Math.random()));

        for(int i=0; i< cumulativeProbabilities.size() - 1; i++){
            if(cumulativeProbabilities.get(i) >= choice && choice > cumulativeProbabilities.get(i+1)){
                System.out.println(cumulativeProbabilities.get(i+1)+"< "+choice+ "<=" + cumulativeProbabilities.get(i));
                nextNode=possibleNodes.get(i);
                break;
            }
            else if(i == cumulativeProbabilities.size() - 2){
                if(choice <= cumulativeProbabilities.get(i+1))
                System.out.println("0 < " + choice +" <=" + cumulativeProbabilities.get(i+1));
                    nextNode = possibleNodes.get(i+1);
            }
        }
        System.out.println("Next Node: " + nextNode);
        return nextNode;
    }

    private int choosePath(WeightedGraph graph, Constant file, FeromonasGraph feromonas) {

        List<Integer> possibleNodes = new ArrayList<>();

        for (int i = 0; i < graph.getNodeNumber(); i++) {
            if (graph.getWeight(currentNode, i) != 0)
                if (!visitedNodes.contains(i)) {
                    possibleNodes.add(i);
                }
        }

        System.out.println("Possible Nodes size: " + possibleNodes.size());

        //all adjacent nodes are visited

        if(possibleNodes.size() == 0 && visitedNodes.size() == graph.getNodeNumber() && graph.getWeight(currentNode, startNode) != 0) {
            System.out.println("All nodes visited");
            int nextNode = startNode;
            return nextNode;
        }

        if (possibleNodes.size() == 0) {
            int nextNode = findPath(graph);
            return nextNode;
        }
        if(possibleNodes.size() == 1)
        {
            System.out.println("Only one possible node; node: " + possibleNodes.get(0) + " will be chosen");
            int nextNode = possibleNodes.get(0);
            return nextNode;
        }
        int nextNode = chooseNextNode(graph, file, feromonas, possibleNodes);

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

    public void createAnt(int AntNumber, int startNode, int nodeNumber) {
        for (int i = 0; i < AntNumber; i++) {
            ACOAnt ant = new ACOAnt(nodeNumber, startNode);
            ants.add(ant);
        }
    }

    public void moveAnts(WeightedGraph graph,FeromonasGraph feromonas, Constant file) {
        int i=0;
        for (ACOAnt ant : ants) {
            i++;
            System.out.println("Ant: " + i);
            //System.out.println("BATATA MAS NAO E FRITA E NAO E DOCE E NAO E SALGADA E NAO E AZEDA E NAO E AMARGA E A BATATA DOCE E UMA DELICIAAAAAAAAAAAAAAAAA MUITO GOSTOSA E MUITO SAUDAVEL");
            ant.move(graph,feromonas,file);
            feromonas.showMatrix();
        }
    }

    public void findPath(WeightedGraph graph) {
        for (ACOAnt ant : ants) {
            ant.findPath(graph);
        }
    }

}