package pec;
import java.util.Arrays;
import java.util.List;

/**
 * The Observation class represents an observation responsible for "observing" the events and printing the results
 */
public class Observation {
    private int countMove;
    private int countEvaporation;
    private int[][] candidates;
    private int[] weight;

    /**
     * Constructs an Observation object with the specified total number of nodes.
     *
     * @param totalNodes The total number of nodes.
     */
    public Observation(int totalNodes) {
        this.countMove = 0;
        this.countEvaporation = 0;
        this.candidates = new int[3][totalNodes];
        this.weight = new int[3];
    }

    /**
     * Updates the move count.
     */
    public void updateMove() {
        this.countMove += 1;
    }

    /**
     * Updates the evaporation count.
     */
    public void updateEvaporation() {
        this.countEvaporation += 1;
    }

    /**
     * Returns the move count.
     *
     * @return The move count.
     */
    public int getMove() {
        return countMove;
    }

    /**
     * Returns the evaporation count.
     *
     * @return The evaporation count.
     */
    public int getEvaporation() {
        return countEvaporation;
    }

    /**
     * Prints the candidates and their weights.
     */
    public void printCandidates() {


        for (int i = 0; i < candidates.length; i++) {

        boolean isCompletelyFilledWithZeros = true;
        for (int k = 0; k < candidates[i].length; k++) {
            if (candidates[i][k] != 0) {
                isCompletelyFilledWithZeros = false;
                break;
            }
        }
        if (isCompletelyFilledWithZeros) {
            System.out.println("{}");
        } else { 
            System.out.print("{");
            for (int j = 0; j < candidates[i].length; j++) {
                System.out.print(candidates[i][j] + 1);
                if (j < candidates[i].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("}:" + weight[i]);
        }
            System.out.println();
        }
    }

    /**
     * Prints the best candidate and its weight.
     */
    public void printBest() {


        boolean isCompletelyFilledWithZeros = true;
        for (int i = 0; i < candidates[0].length; i++) {
            if (candidates[0][i] != 0) {
                isCompletelyFilledWithZeros = false;
                break;
            }
        }
        if (isCompletelyFilledWithZeros) {
            System.out.println("{}");
            return;
        } else {
            System.out.print("{");
        for (int j = 0; j < candidates[0].length; j++) {
            
            System.out.print(candidates[0][j] + 1);
            if (j < candidates[0].length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("}:" + weight[0]);
        }
        System.out.println();
    }

    /**
     * Checks the current path and updates the candidates if necessary.
     *
     * @param currentPath The current path.
     * @param pathWeight  The weight of the path.
     */
    public void checkPath(List<Integer> currentPath, int pathWeight) {
        int[] bck = new int[candidates[0].length];
        int[] bck2 = new int[candidates[0].length];
        int weightBck;
        int weightBck2;

        // corre pelos melhores caminhos encontrados até agora
        for (int i = 0; i < 3; i++) {
            // se o peso do caminho atual for menor que o peso do caminho guardado, ou se o peso do caminho guardado for 0, é
            //feita a troca
            if (weight[i] > pathWeight || weight[i] == 0) {
                bck = candidates[i].clone();
                weightBck = weight[i];
                // caso o caminho a trocar esteja na terceira posição, é feita a troca imediatamente
                if (i == 2) {
                    for (int j = 0; j < currentPath.size() - 1; j++) {
                        candidates[i][j] = currentPath.get(j);
                        weight[i] = pathWeight;
                    }
                    break;
                // caso o caminho a trocar esteja na segunda posição, o caminho previamente na segunda posição passa para a terceira
                //posição e o caminho atual passa para a segunda posição
                } else if (i == 1) {
                    for (int j = 0; j < currentPath.size() - 1; j++) {
                        candidates[i][j] = currentPath.get(j);
                        weight[i] = pathWeight;
                        candidates[i + 1][j] = bck[j];
                        weight[i + 1] = weightBck;
                    }
                    break;

                // caso o caminho a trocar esteja na primeira posição, todos os caminhos dão um "shift" para baixo: 1º para 2º, 2º para 3º
                // 3º... e o caminho atual passa para a primeira posição 
                } else {
                    bck2 = candidates[i + 1].clone();
                    weightBck2 = weight[i + 1];
                    for (int j = 0; j < currentPath.size() - 1; j++) {
                        candidates[i][j] = currentPath.get(j);
                        weight[i] = pathWeight;
                        candidates[i + 1][j] = bck[j];
                        weight[i + 1] = weightBck;
                        candidates[i + 2][j] = bck2[j];
                        weight[i + 2] = weightBck2;
                    }
                    break;
                }
            // caso o peso do caminho atual seja igual ao peso do caminho guardado, 
            // verifica-se se o caminho atual é igual ao caminho guardado, e se for este é descartado
            } else if (weight[i] == pathWeight) {
                for (int j = 0; j < currentPath.size() - 1; j++) {
                    bck[j] = currentPath.get(j);
                }
                boolean arraysEqual = Arrays.equals(bck, candidates[i]);
                if (arraysEqual) {
                    break;
                }
            }
        }
    }
}
