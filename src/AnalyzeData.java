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
        adjacencyMatrix[node1][node2] = (int)weight;
        adjacencyMatrix[node2][node1] = (int)weight;
    }

    @Override
    public float getWeight(int node1, int node2) {
        return (int) adjacencyMatrix[node1][node2];
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
        for (int i = 0; i < n; i++) {
            for(int j = i+1; j < n ; j++) {
                int weight = (int) (Math.random() * max_n);
                graphPesoAnt.addEdge(i, j, weight);
                graphFeromonas.addEdge(i, j, 0);
            }
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