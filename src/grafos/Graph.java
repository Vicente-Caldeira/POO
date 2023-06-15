package grafos;


/**
 * The Graph class represents a graph.
 * It provides basic functionality for graphs.
 */
public abstract class Graph {
    private int nodeNumber;

    /**
     * Constructs a Graph object with the specified number of nodes.
     *
     * @param n       The number of nodes in the graph.
     */
    Graph(int n){
        this.nodeNumber = n;
    }

    /**
     * prints the number of nodes in the graph.
     *
     */
    public void showNodeNUmber(){
        System.out.println("Node Number:" + nodeNumber);
    }
    
    /**
     * returns the number of nodes in the graph
     *
     * @return          the number of nodes in the graph
     */
    public int getNodeNumber(){
        return nodeNumber;
    }

    /**
     * adds the edge between node1 and node2 to the graph.
     *
     * @param node1     the origin node.
     * @param node2     the destination node.
     * @param weight    the weight of the edge.
     */
    public abstract void addEdge(int node1, int node2, float weight);

    /**
     * returns the weight of the edge between node1 and node2.
     *
     * @param node1     the origin node.
     * @param node2     the destination node.
     * @return          the weight of the edge.
     */
    public abstract float getWeight(int node1, int node2);
}