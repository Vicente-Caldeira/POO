import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

abstract class Graph {
    private int nodeNumber;

    Graph(int n){
        this.nodeNumber = n;
    }
    public void showNodeNUmber(){
        System.out.println("Node Number:" + nodeNumber);
    }
    
    public int getNodeNumber(){
        return nodeNumber;
    }
    
    public abstract void addEdge(int node1, int node2, float weight);
    public abstract float getWeight(int node1, int node2);
}

public class WeightedGraph extends Graph {
    private final int[][] adjacencyMatrix;
    private int maxWeight;


    WeightedGraph(int n,int max_n) {
        super(n);
        this.adjacencyMatrix = new int[n][n];
        this.maxWeight = max_n;
    }

    @Override
    public void addEdge(int node1, int node2, float weight) {
        adjacencyMatrix[node1][node2] = (int) weight;
        adjacencyMatrix[node2][node1] = (int) weight;
    }

    @Override
    public float getWeight(int node1, int node2) {
        return adjacencyMatrix[node1][node2];
    }
    // Method to get the adjacency matrix.
    /*public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }*/
    public void showMaxWeight(){
        System.out.println("Max Weight:" + maxWeight);
    }
    public int getMaxWeight(){
            return maxWeight;
    }
    public void showMatrix(){
        for (int[] row : this.adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    public int numCasa(int n){
        int z = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(adjacencyMatrix[i][j] != 0)
                    z++;
            }
        }
        return z;
    }
    
    public int totalWeightGraph(){
        int count = 0;
        for (int i = 0; i < this.getNodeNumber(); i++){
            for (int j = i; j < this.getNodeNumber(); j++){
                count += adjacencyMatrix[i][j];
            }
        }
        return count;
    }
    public void createRandomMatrix(WeightedGraph pesoAnt, FeromonasGraph feromonasAnt){
        // Connect each node to the next node
        for (int i = 0; i < pesoAnt.getNodeNumber() - 1; i++) {
            int weight;
            do{
                weight = (int) (Math.random() * pesoAnt.getMaxWeight());
            }while(weight == 0);
            pesoAnt.addEdge(i, i + 1, weight);
            feromonasAnt.addEdge(i, i + 1, 0);
        }
        // Connect the last node to the first node to create a Hamiltonian cycle
        int weight;
        do{
            weight = (int) (Math.random() * pesoAnt.getMaxWeight());
        }while(weight == 0);

        int n = pesoAnt.getNodeNumber();
        pesoAnt.addEdge(n - 1, 0, weight);
        feromonasAnt.addEdge(n - 1, 0, 0);

        // Sample random edges
        int Y = n + (int) (Math.random() * (n * (n - 1) / 2 - n));
    
        // Cria todas as combinações possíveis de node1 e node2
        List<int[]> combinations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                combinations.add(new int[]{i, j});
            }
        }

        // Embaralha a lista de combinações
        Collections.shuffle(combinations);

        // Cria um conjunto para armazenar as combinações já selecionadas
        Set<Integer> selectedCombinations = new HashSet<>();

        // Seleciona uma combinação aleatória a cada iteração
        for (int i = 0; i < Y - n; i++) {
            int combinationIndex;
            int node1;
            int node2;
        
            do {
                combinationIndex = (int) (Math.random() * combinations.size());
                int[] randomCombination = combinations.get(combinationIndex);
                node1 = randomCombination[0];
                node2 = randomCombination[1];
            } while (selectedCombinations.contains(combinationIndex) || pesoAnt.getWeight(node1, node2) != 0);
        
            selectedCombinations.add(combinationIndex);
        
            do {
                weight = (int) (Math.random() * pesoAnt.getMaxWeight());
            } while (weight == 0);
        
            pesoAnt.addEdge(node1, node2, weight);
            feromonasAnt.addEdge(node1, node2, 0);
        }
    }
}

class AnalyzeData {

    public static void main(String[] args) {
        //int n = 5;
        //int max_n = 20;
        WeightedGraph graphPesoAnt = new WeightedGraph(10,20);
        FeromonasGraph graphFeromonas = new FeromonasGraph(10);

        // Connect each node to the next node
        for (int i = 0; i < graphPesoAnt.getNodeNumber() - 1; i++) {
            int weight;
            do{
                weight = (int) (Math.random() * graphPesoAnt.getMaxWeight());
            }while(weight == 0);
            graphPesoAnt.addEdge(i, i + 1, weight);
            graphFeromonas.addEdge(i, i + 1, 0);
        }
        // Connect the last node to the first node to create a Hamiltonian cycle
        int weight;
        do{
            weight = (int) (Math.random() * graphPesoAnt.getMaxWeight());
        }while(weight == 0);
        int n = graphPesoAnt.getNodeNumber();
        graphPesoAnt.addEdge(n - 1, 0, weight);
        graphFeromonas.addEdge(n - 1, 0, 0);

        // Sample random edges
        int Y = n + (int) (Math.random() * (n * (n - 1) / 2 - n));
    
        // Cria todas as combinações possíveis de node1 e node2
        List<int[]> combinations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                combinations.add(new int[]{i, j});
            }
        }

        // Embaralha a lista de combinações
        Collections.shuffle(combinations);

        // Cria um conjunto para armazenar as combinações já selecionadas
        Set<Integer> selectedCombinations = new HashSet<>();

        // Seleciona uma combinação aleatória a cada iteração
        for (int i = 0; i < Y - n; i++) {
            int combinationIndex;
            int node1;
            int node2;
        
            do {
                combinationIndex = (int) (Math.random() * combinations.size());
                int[] randomCombination = combinations.get(combinationIndex);
                node1 = randomCombination[0];
                node2 = randomCombination[1];
            } while (selectedCombinations.contains(combinationIndex) || graphPesoAnt.getWeight(node1, node2) != 0);
        
            selectedCombinations.add(combinationIndex);
        
            do {
                weight = (int) (Math.random() * graphPesoAnt.getMaxWeight());
            } while (weight == 0);
        
            graphPesoAnt.addEdge(node1, node2, weight);
            graphFeromonas.addEdge(node1, node2, 0);
        }

        // graphPesoAnt.showMatrix();
        // graphFeromonas.showMatrix();
        // ACOAnt AntColony = new ACOAnt(graphPesoAnt.getNodeNumber(), 5);
        // AntColony.move(graphPesoAnt,graphFeromonas);

        System.out.println("Melhor caminho: ");
    }
}