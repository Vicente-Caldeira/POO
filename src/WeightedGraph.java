
import java.util.Arrays;

abstract class Graph {
    private int nodeNumber;
    private int maxWeight;

    Graph(int n, int max_n){
        this.nodeNumber = n;
        this.maxWeight = max_n;
    }
    public void showNodeNUmber(){
        System.out.println("Node Number:" + nodeNumber);
    }
    public void showMaxWeight(){
        System.out.println("Max Weight:" + maxWeight);
    }
    public int getNodeNumber(){
        return nodeNumber;
    }
    public int getMaxWeight(){
        return maxWeight;
    }
    public abstract void addEdge(int node1, int node2, float weight);
    public abstract float getWeight(int node1, int node2);
}

public class WeightedGraph extends Graph {
    private final int[][] adjacencyMatrix;

    WeightedGraph(int n,int max_n) {
        super(n,max_n);
        this.adjacencyMatrix = new int[n][n];
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
    public void showMatrix(){
        for (int[] row : this.adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

class FeromonasGraph extends Graph {
    private final float[][] adjacencyMatrix;

    FeromonasGraph(int n,int max_n) {
        super(n,max_n);
        this.adjacencyMatrix = new float[n][n];
    }

    @Override
    public void addEdge(int node1, int node2, float weight) {
        adjacencyMatrix[node1][node2] = weight;
        adjacencyMatrix[node2][node1] = weight;
    }

    @Override
    public float getWeight(int node1, int node2) {
        return adjacencyMatrix[node1][node2];
    }

    // Method to get the adjacency matrix.
    /*public float[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }*/
    public void showMatrix(){
        for (float[] row : this.adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

class AnalyzeData {

    public static void main(String[] args) {
        //int n = 5;
        //int max_n = 20;
        WeightedGraph graphPesoAnt = new WeightedGraph(5,20);
        FeromonasGraph graphFeromonas = new FeromonasGraph(5,20);

        // Connect each node to the next node
        for (int i = 0; i < graphPesoAnt.getNodeNumber() - 1; i++) {
            int weight = (int) (Math.random() * graphPesoAnt.getMaxWeight());
            graphPesoAnt.addEdge(i, i + 1, weight);
            graphFeromonas.addEdge(i, i + 1, 0);
        }
        // Connect the last node to the first node to create a Hamiltonian cycle
        int weight = (int) (Math.random() * graphPesoAnt.getMaxWeight());
        int n = graphPesoAnt.getNodeNumber();
        graphPesoAnt.addEdge(n - 1, 0, weight);
        graphFeromonas.addEdge(n - 1, 0, 0);

        // Sample random edges
        int Y = n + (int) (Math.random() * (n * (n - 1) / 2 - n));
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        for (int i = 0; i < Y - n; i++) {
            int j = i + (int) (Math.random() * (n - i));
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }
        for (int i = 0; i < Y - n; i++) {
            int node1 = indices[i];
            int node2 = indices[i + 1];
            weight = (int) (Math.random() * graphPesoAnt.getMaxWeight());
            graphPesoAnt.addEdge(node1, node2, weight);
            graphFeromonas.addEdge(node1, node2, 0);
        }

        //int[][] graph_peso = graphPesoAnt.getAdjacencyMatrix();
        graphPesoAnt.showMatrix();
        graphFeromonas.showMatrix();
        /*for (int[] row : graph_peso) {
            System.out.println(Arrays.toString(row));
        }

        float[][] graph_feromonas = graphFeromonas.getAdjacencyMatrix();

        for (float[] row : graph_feromonas) {
            System.out.println(Arrays.toString(row));
        }*/
    }
}