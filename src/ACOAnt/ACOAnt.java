package ACOAnt;
import grafos.WeightedGraph;
import pec.Observation;
import java.util.*;

import Main.Constant;
import pec.Queue;
import grafos.FeromonasGraph;
import grafos.FeromonasCycle;


/**
 * The ACOAnt class is responsible for the ant's behaviour. More specifically, 
 * it is responsible for the ant's movement and for the ant's path.
 */
public class ACOAnt{

    private int currentNode;
    private Set<Integer> visitedNodes;
    private List<Integer> currentPath;
    private int startNode;
    private WeightedGraph graph;
    private float alpha;
    private float beta;
    private float delta;
    private Observation observer;

    /**
     * Constructs an ACOAnt object with the specified parameters.
     * 
     * @param n the number of nodes
     * @param initialNode the nest node
     * @param graph the weighted graph
     * @param file the constant file
     * @param observer the observer, responsible for "observing" the events and printing the results
     */
    ACOAnt(int n, int initialNode,WeightedGraph graph,Constant file, Observation observer){

        this.visitedNodes = new HashSet<>(n);
        this.currentPath = new ArrayList<>(n);
        this.currentNode = initialNode;
        this.visitedNodes.add(initialNode);
        this.currentPath.add(initialNode);
        this.startNode = initialNode;
        this.graph = graph;
        this.alpha = file.getaplha();
        this.beta = file.getbeta();
        this.delta = file.getdelta();
        this.observer=observer;
    }

    /**
     * returns the current path of the ant
     * 
     * @return the current path of the ant
     */
    public List<Integer> getCurrentPath(){
        return this.currentPath;
    }

    /**
     * resets the current path of the ant, after the nest has been reached
     * 
     * @param count the total weight of the current path
     */
    public void resetPaths(int count) {

        observer.checkPath(currentPath, count);

        int size = currentPath.size();
        List<Integer> subList = currentPath.subList(0, size);
        visitedNodes.removeAll(subList);
        subList.clear();

        currentPath.add(startNode);
        visitedNodes.add(startNode);

        return;
    }

    /**
     * the move method is responsible for monitoring the path of the ant, and deciding what the ant will do next
     * 
     * @param list the queue of events
     * @param feromonas the feromonas graph
     * @param file the constant file
     * @param time the time
     * @return the time that an ant will take to complete the edge
     */
    public double move(Queue list, FeromonasGraph feromonas, Constant file, double time) {
        
        //escolhe o proximo nó
        int nextNode = choosePath(feromonas);
        double edgeTime = 0.0;
        if (currentPath.size() < graph.getNodeNumber()+1 && nextNode != -1) {
            visitedNodes.add(nextNode);
            currentPath.add(nextNode);

            //calcula o tempo que a formiga demora a percorrer a aresta
            edgeTime = graph.getWeight(currentNode, nextNode) * delta;

            currentNode = nextNode;
        }// caso a formiga complete um ciclo
        else {

            int count = 0;
            //calcula o peso do ciclo
            for(int i = 0; i < this.currentPath.size() - 1; i++){
                count += graph.getWeight(this.currentPath.get(i),this.currentPath.get(i + 1));
            }
            // adiciona as feromonas na matriz
            FeromonasCycle updateFeromonas = new FeromonasCycle(graph.totalWeightGraph(), count,feromonas);
            updateFeromonas.updateFeromonas(list,file,this.currentPath,time,observer);
            resetPaths(count);

        
        }
        return edgeTime;
    }

    /**
     * the findPath method is responsible for choosing the next node that the ant will visit, in case a dead end has been reached
     * 
     * @param graph the weighted edged graph
     * @return the next node that the ant will visit
     */
    public int findPath(WeightedGraph graph) {

        List<Integer> possibleNodes = new ArrayList<>();

        // guarda uma lista de todos os nós possiveis
        for (int i = 0; i < graph.getNodeNumber(); i++) {
            if (graph.getWeight(currentNode, i) != 0)
                possibleNodes.add(i);
        }

        int totalOptions=possibleNodes.size();

        // caso não existam nós possiveis, é escolhido um dos nós adjacentes ao acaso
        int nextNode = possibleNodes.get((int) (Math.random() * totalOptions));

        update_vectors(nextNode);

        return nextNode;
    }

    /**
     * the update_vectors method is responsible for updating the vectors of the ant, in case a "dead end" has been reached.
     * 
     * @param nextNode the next node that the ant will visit
     */
    private void update_vectors(int nextNode) {

        // Apaga todos os nós vistos posteriormente ao nó escolhido para visitar, e apaga o caminho redundante
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

    /**
     * the chooseNextNode method is responsible for choosing the next node that the ant will visit, based on the current pheromones
     * 
     * @param feromonas the feromonas graph
     * @param possibleNodes the list of possible nodes
     * 
     * @return the next node that the ant will visit
     */
    private int chooseNextNode(FeromonasGraph feromonas, List<Integer> possibleNodes){
        int nextNode = 0;
        int totalOptions=possibleNodes.size();

        List<Float> cijk = new ArrayList<>();

        float ci=0;

        // calcula o cijk e ci, os parametros necessarios para calcular a probabilidade de escolher um nó
        for(int i=0; i<totalOptions; i++){
            float weight=graph.getWeight(currentNode,possibleNodes.get(i));
            float cij=(alpha+feromonas.getWeight(currentNode,possibleNodes.get(i)))/(beta+weight);
            cijk.add(cij);
            ci+=cij;
        }
        
        // baseado nos parametros calculados, calcula a probabilidade de escolher cada um dos nós adjacentes
        List<Float> probabilities = new ArrayList<>();
        for(int i=0; i<totalOptions; i++){
            float probability=cijk.get(i)/ci;
            probabilities.add(probability);
        }

        // calcula a probabilidade cumulativa de escolher cada um dos nós adjacentes
        List<Float> cumulativeProbabilities = new ArrayList<>();
        for(int i=0; i<totalOptions; i++){
            float cumulativeProbability=0;
            for(int j=i; j<totalOptions; j++){
                cumulativeProbability+=probabilities.get(j);
            }
            cumulativeProbabilities.add(cumulativeProbability);
        }

        // escolhe o nó baseado na probabilidade cumulativa
        float choice = ((float) (Math.random()));
        for(int i=0; i< cumulativeProbabilities.size() - 1; i++){
            if(cumulativeProbabilities.get(i) >= choice && choice > cumulativeProbabilities.get(i+1)){
                nextNode=possibleNodes.get(i);
                break;
            }
            else if(i == cumulativeProbabilities.size() - 2){
                if(choice <= cumulativeProbabilities.get(i+1))
                    nextNode = possibleNodes.get(i+1);
            }
        }
        return nextNode;
    }

    /**
     * the choosePath method is responsible for overseeing the movements of the ant, and choosing if
     * the next node is new or a repeated one
     * 
     * @param feromonas the feromonas graph
     * 
     * @return the next node that the ant will visit
     */
    private int choosePath(FeromonasGraph feromonas) {

        List<Integer> possibleNodes = new ArrayList<>();
        //guarda todos os nós adjacentes
        for (int i = 0; i < graph.getNodeNumber(); i++) {
            if (graph.getWeight(currentNode, i) != 0)
                if (!visitedNodes.contains(i)) {
                    possibleNodes.add(i);
                }
        }

        // caso todos os nós tenham sido visitados, e o nó atual seja o nó inicial, o caminho é terminado
        if(possibleNodes.size() == 0 && visitedNodes.size() == graph.getNodeNumber() && graph.getWeight(currentNode, startNode) != 0 && currentNode != startNode) {
            currentPath.add(startNode);

            return -1;
        }

        // caso não exista nenhum nó adjacente por visitar, é escolhido um dos nós adjacentes ao acaso
        if (possibleNodes.size() == 0) {
            int nextNode = findPath(graph);
            return nextNode;
        }

        // caso exista apenas um nó adjacente por visitar, é escolhido esse nó
        if(possibleNodes.size() == 1)
        {
            int nextNode = possibleNodes.get(0);
            return nextNode;
        }

        int nextNode = chooseNextNode(feromonas, possibleNodes);

        return nextNode;
    }

}