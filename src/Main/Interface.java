package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import grafos.FeromonasGraph;
import grafos.WeightedGraph;
import pec.Event;
import pec.EventEnd;
import pec.EventObservation;
import pec.Observation;
import pec.Queue;
import ACOAnt.Colony;


 /**
 * The Interface class is responsible for executing the program based on user input.
 */

public class Interface {
    
    /**
     * The main method is the entry point of the program.
     * It checks the user input and calls the appropriate method.
     *
     * @param args The command-line arguments provided by the user.
     */
    public static void main(String[] args) {
        if (args.length == 2)
            withFile(args);
        else if (args.length == 12)
            withInput(args);
        else
            System.out.println("Incorrect user input.");
    }


    /**
     * Executes the program using input from a file.
     *
     * @param args The command-line arguments provided by the user.
     */

    public static void withFile(String[] args) {
        Constant programmConstant = new Constant();
        Queue filaEventQueue = new Queue();
        WeightedGraph graphPesoAnt;
        FeromonasGraph feromonasGraph;
        Observation simulationObserver;
        // Verificar os argumentos
        if (!args[0].equals("-f"))
            System.out.println("Incorrect indicator, should be -f.");
        if (!args[1].endsWith(".txt")) {
            System.out.println("The file is an incorrect format, should be a .txt file.");
        }// Ler e extrair informação do ficheiro
        else{
            File file = new File(args[1]);
            try {
                Scanner scanner = new Scanner(file);
                String line = scanner.nextLine();
                String[] part = line.split(" ");

                //Extrair todos os parâmetros do ficheiro

                programmConstant.setNodeNumber(Integer.parseInt(part[0]));
                programmConstant.setNodeInit(Integer.parseInt(part[1]));
                programmConstant.setalpha(Float.parseFloat(part[2]));
                programmConstant.setbeta(Float.parseFloat(part[3]));
                programmConstant.setdelta(Float.parseFloat(part[4]));
                programmConstant.seteta(Float.parseFloat(part[5]));
                programmConstant.setrho(Float.parseFloat(part[6]));
                programmConstant.setgamma(Float.parseFloat(part[7]));
                programmConstant.setnu(Float.parseFloat(part[8]));
                programmConstant.settau(Float.parseFloat(part[9]));

                programmConstant.print();

                graphPesoAnt= new WeightedGraph(programmConstant.getNodeNumber(),programmConstant.getNodeInit());
                feromonasGraph= new FeromonasGraph(programmConstant.getNodeNumber());
                simulationObserver = new Observation(programmConstant.getNodeNumber());
                int i = 0;
                int j = 0;

                //Copiar a Matriz
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    part = line.split(" ");
                    for(j = 0; j < graphPesoAnt.getNodeNumber(); j++){
                        graphPesoAnt.addEdge(i, j, Integer.parseInt(part[j]));
                        feromonasGraph.addEdge(i,j,0.0F);
                    }
                    i++;
                    System.out.println(line);
                }
                System.out.println();
                System.out.println("with graph:");

                graphPesoAnt.showMatrix();
                scanner.close();

                // Inicio da simulação

                Colony AntColony = new Colony(feromonasGraph);
                AntColony.createAnt(((int) programmConstant.getnu()), programmConstant.getNodeInit(), programmConstant.getNodeNumber(),filaEventQueue,graphPesoAnt,programmConstant,simulationObserver);
                for(int k = 1; k < 20; k++){
                    filaEventQueue.add(new EventObservation(k*programmConstant.gettau()/20, k, simulationObserver));
                }
                filaEventQueue.add(new EventEnd(programmConstant.gettau(),simulationObserver, 20));
                while(filaEventQueue.size() > 0){
                    Event run = filaEventQueue.next();
                    run.runEvent(filaEventQueue);
                }
         
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            }
        }
    }


    /**
     * Executes the program using user input from the command line.
     *
     * @param args The command-line arguments provided by the user.
     */
    public static void withInput(String[] args) {
        if (!args[0].equals("-r")){
                System.out.println("Incorrect indicator, should be -r.");
                System.out.println("Args[0]: " + args[0]);            
            }
        else{
            Constant programmConstant = new Constant();
            Queue filaEventQueue = new Queue();
            WeightedGraph graphPesoAnt;
            FeromonasGraph feromonasGraph;
            Observation simulationObserver;

            //Extrair todos os parâmetros do ficheiro

            programmConstant.setNodeNumber(Integer.parseInt(args[1]));
            programmConstant.setmaxWeight(Integer.parseInt(args[2]));
            programmConstant.setNodeInit(Integer.parseInt(args[3]));
            programmConstant.setalpha(Float.parseFloat(args[4]));
            programmConstant.setbeta(Float.parseFloat(args[5]));
            programmConstant.setdelta(Float.parseFloat(args[6]));
            programmConstant.seteta(Float.parseFloat(args[7]));
            programmConstant.setrho(Float.parseFloat(args[8]));
            programmConstant.setgamma(Float.parseFloat(args[9]));
            programmConstant.setnu(Float.parseFloat(args[10]));
            programmConstant.settau(Float.parseFloat(args[11]));

            

            programmConstant.print();

            graphPesoAnt= new WeightedGraph(programmConstant.getNodeNumber(),programmConstant.getmaxWeight());
            feromonasGraph = new FeromonasGraph(programmConstant.getNodeNumber());
            simulationObserver = new Observation(programmConstant.getNodeNumber());

            graphPesoAnt.createRandomMatrix(graphPesoAnt,feromonasGraph);
            System.out.println();
            System.out.println("with graph:");
            graphPesoAnt.showMatrix();

            // Inicio da simulação

            Colony AntColony = new Colony(feromonasGraph);
            AntColony.createAnt(((int) programmConstant.getnu()), programmConstant.getNodeInit(), programmConstant.getNodeNumber(),filaEventQueue,graphPesoAnt,programmConstant,simulationObserver);
            for(int k = 1; k < 20; k++){
                filaEventQueue.add(new EventObservation(k*programmConstant.gettau()/20, k, simulationObserver));
            }
            filaEventQueue.add(new EventEnd(programmConstant.gettau(),simulationObserver, 20));
            while(filaEventQueue.size() > 0){
                Event run = filaEventQueue.next();
                run.runEvent(filaEventQueue);
            }

        }        
    }
}







