import java.util.Arrays;

public class FeromonasGraph extends Graph {
    private final float[][] adjacencyMatrix;

    FeromonasGraph(int n) {
        super(n);
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