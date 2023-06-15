package grafos;
import java.util.Arrays;

/**
 * The FeromonasGraph class extends the Graph class and provides additional functionality for the pheromones graph.
 */
public class FeromonasGraph extends Graph {
    private final float[][] adjacencyMatrix;

    /**
     * Constructs a FeromonasGraph object with the specified number of nodes.
     *
     * @param n       The number of nodes in the graph.
     */
    public FeromonasGraph(int n) {
        super(n);
        this.adjacencyMatrix = new float[n][n];
    }

    /**
     * adds the pheromones edge between node1 and node2 to the graph.
     *
     * @param node1     the origin node.
     * @param node2     the destination node.
     * @param weight    the weight of the edge.
     */
    @Override
    public void addEdge(int node1, int node2, float weight) {
        adjacencyMatrix[node1][node2] = weight;
        adjacencyMatrix[node2][node1] = weight;
    }

    /**
     * returns the weight of the pheromones edge between node1 and node2.
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
     * prints the pheromones matrix.
     *
     */
    public void showMatrix(){
        for (float[] row : this.adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}