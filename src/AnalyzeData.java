import java.util.Arrays;

interface Graph {
    void addEdge(int node1, int node2, float weight);
    float getWeight(int node1, int node2);
}

class WeightedGraph implements Graph {
    private final int[][] adjacencyMatrix;

    WeightedGraph(int n) {
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
    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}

class FeromonasGraph implements Graph {
    private final float[][] adjacencyMatrix;

    FeromonasGraph(int n) {
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
    public float[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}

public class AnalyzeData {

    public static void main(String[] args) {
        int n = 5;
        int max_n = 100;
        WeightedGraph graphPesoAnt = new WeightedGraph(n);
        FeromonasGraph graphFeromonas = new FeromonasGraph(n);

        // Connect each node to the next node
        for (int i = 0; i < n - 1; i++) {
            int weight = (int) (Math.random() * max_n);
            graphPesoAnt.addEdge(i, i + 1, weight);
            graphFeromonas.addEdge(i, i + 1, 0);
        }
        // Connect the last node to the first node to create a Hamiltonian cycle
        int weight = (int) (Math.random() * max_n);
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
            weight = (int) (Math.random() * max_n);
            graphPesoAnt.addEdge(indices[i], indices[j], weight);
            graphFeromonas.addEdge(indices[i], indices[j], 0);
        }

        int[][] graph_peso = graphPesoAnt.getAdjacencyMatrix();

        for (int[] row : graph_peso) {
            System.out.println(Arrays.toString(row));
        }

        float[][] graph_feromonas = graphFeromonas.getAdjacencyMatrix();

        for (float[] row : graph_feromonas) {
            System.out.println(Arrays.toString(row));
        }
    }
}