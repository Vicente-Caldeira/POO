import java.util.Arrays;
import java.util.List;

public class Observation {
    private int countMove;
    private int countEvaporation;
    private int[][] candidates;
    private int[] weight;

    Observation(int TotalNodes){
        this.countMove = 0;
        this.countEvaporation = 0;
        this.candidates = new int[3][TotalNodes];
        this.weight = new int[3];
    }
    public void updateMove(){
        this.countMove += 1;
    }
    public void updateEvaporation(){
        this.countEvaporation += 1;
    }
    public int getMove(){
        return countMove;
    }
    public int getEvaporation(){
        return countEvaporation;
    }
    public void printCandidates(){
        for (int i = 0; i < candidates.length; i++) {
            System.out.print("{");
            for (int j = 0; j < candidates[i].length; j++) {
                System.out.print(candidates[i][j]);
                if (j < candidates[i].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("}:" + weight[i]);
            System.out.println();
        }
    }
    public void printBest(){
        System.out.print("{");
        for (int j = 0; j < candidates[0].length; j++) {
                System.out.print(candidates[0][j]);
                if (j < candidates[0].length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("}:" + weight[0]);
            System.out.println();
    }
    public void checkPath(List<Integer> currentPath, int pathWeight){
        int[] bck = new int[candidates[0].length];
        int[] bck2 = new int[candidates[0].length];
        int weightBck;
        int weightBck2;
        for(int i = 0; i < 3; i++){
            System.out.println("Weight: " + weight[i] + "path weight: " + pathWeight + " i: " + i);
            if(weight[i] > pathWeight || weight[i] == 0) {
                bck=candidates[i].clone();
                weightBck = weight[i];
                if(i == 2){
                        for(int j=0; j < currentPath.size() - 1; j++) {
                        System.out.println("j: " + j);
                        candidates[i][j]=currentPath.get(j);
                        weight[i] = pathWeight;
                                            
                    }
                    break;   
                }
                else if(i == 1){
                    for(int j=0; j < currentPath.size() - 1; j++) {
                        System.out.println("j: " + j);
                        candidates[i][j]=currentPath.get(j);
                        weight[i] = pathWeight;
                        candidates[i+1][j]=bck[j];
                        weight[i+1] = weightBck;
                        
                    }       
                    break;           
                }
                else{
                    bck2=candidates[i+1].clone();
                    weightBck2 = weight[i+1];
                    for(int j=0; j < currentPath.size() - 1; j++) {
                    System.out.println("j: " + j);
                        candidates[i][j]=currentPath.get(j);
                        weight[i] = pathWeight;
                        candidates[i+1][j]=bck[j];
                        weight[i+1] = weightBck;
                        candidates[i+2][j]=bck2[j];
                        weight[i+2] = weightBck2;

                        System.out.println(" Weight: " + weight[i]);
                        
                    }  
                    break;
                }
                
                // System.out.println("Path: " + Arrays.toString(candidates[i]) + " Weight: " + weight[i]);
                // bck=candidates[i].clone();
                // System.out.println("bck: " + Arrays.toString(bck) + " size: " + bck.length);
                // weightBck=weight[i];
                // weight[i]=pathWeight;
                // pathWeight=weightBck;
                // int size=currentPath.size()-1;
                // System.out.println("currentPath: " + currentPath);
                // System.out.println("size: " + size);
                // for(int j=0; j<size; j++) {
                //     System.out.println("j: " + j);
                //     candidates[i][j]=currentPath.get(j);
                // }
                // currentPath.clear();
                // // System.out.println("bck: " + Arrays.toString(bck));
                // // for (int j = 0; j < bck.length; j++) {
                // //     System.out.println("j22q2222: " + j);
                // //     currentPath.set(j,bck[j]);
                // // }

                // System.out.println("Path: " + Arrays.toString(candidates[0]) + " Weight: " + weight[0]);
            }
            else if(weight[i] == pathWeight) {          
                for(int j=0; j<currentPath.size() - 1 ; j++) {
                    bck[j]=currentPath.get(j);
                }

                boolean arraysEqual = Arrays.equals(bck, candidates[i]); 

                if(arraysEqual) {
                    System.out.println("Path previously saved. Discarding...");
                    break;
                }
            }
        }
        printCandidates();
    }

    
    
}
