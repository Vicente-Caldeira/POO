package grafos;
import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


/**
 * The WeightedGraph class represents a graph with the weight of each edge.
 * It extends the Graph class and provides additional functionality for weighted edges.
 */
public class WeightedGraph extends Graph {
    private final int[][] adjacencyMatrix;
    private int maxWeight;

    /**
     * Constructs a WeightedGraph object with the specified number of nodes and maximum weight.
     *
     * @param n       The number of nodes in the graph.
     * @param max_n   The maximum weight value.
     */
    public WeightedGraph(int n,int max_n) {
        super(n);
        this.adjacencyMatrix = new int[n][n];
        this.maxWeight = max_n;
    }

    /**
     * adds the weighted edge between node1 and node2 to the graph.
     *
     * @param node1     the origin node.
     * @param node2     the destination node.
     * @param weight    the weight of the edge.
     */
    @Override
    public void addEdge(int node1, int node2, float weight) {
        adjacencyMatrix[node1][node2] = (int) weight;
        adjacencyMatrix[node2][node1] = (int) weight;
    }

    /**
     * returns the weight of the edge between node1 and node2.
     *
     * @param node1     the origin node.
     * @param node2     the destination node.
     * @return          the weight of the edge.
     */
    @Override
    public float getWeight(int node1, int node2) {
        return adjacencyMatrix[node1][node2];
    }
    
    /**
     * prints the max weight of the graph.
     *
     */
    public void showMaxWeight(){
        System.out.println("Max Weight:" + maxWeight);
    }
    /**
     * returns the max weight of the graph
     *
     * @return          the max weight of the graph
     */
    public int getMaxWeight(){
            return maxWeight;
    }

    /**
     * prints the matrix
     *
     */
    public void showMatrix(){
        for (int[] row : this.adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * obtains the total number of edges in the graph.
     *
     * @param n     the number of nodes in the graph.
     * @return      the number of edges.
     */
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
    
    /**
     * returns the total weight of the graph.
     *
     * @return      the total weight of the graph
     */
    public int totalWeightGraph(){
        int count = 0;
        for (int i = 0; i < this.getNodeNumber(); i++){
            for (int j = i; j < this.getNodeNumber(); j++){
                count += adjacencyMatrix[i][j];
            }
        }
        return count;
    }

    /**
     * given the parameters, it creates a random matrix for the weight, and an empty matrix for the pheromones,
     * according to the hamiltonian cycles rules
     *
     * @param pesoAnt       the graph with the weight of the edges.
     * @param feromonasAnt  the graph with the pheromones of the edges.
     */
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
