import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Constant{
    private int NodeNumber;
    private int maxWeight;
    private int NodeInit;
    private float alpha;
    private float beta;
    private float delta;
    private float eta;
    private float rho;
    private float gamma;
    private float nu;
    private float tau;

    Constant(){
        this.NodeNumber = 0;
        this.NodeInit = 0;
        this.maxWeight = 0;
        this.alpha = 0;
        this.beta = 0;
        this.delta = 0;
        this.eta = 0;
        this.rho = 0;
        this.gamma = 0;
        this.nu = 0;
        this.tau = 0;
    }

    public void setNodeNumber(int NodeNUmber){
        this.NodeNumber = NodeNUmber;
    }
    public void setmaxWeight(int maxWeight){
        this.maxWeight = maxWeight;
    }
    public void setNodeInit(int NodeInit){
        this.NodeInit = NodeInit;
    }
    public void setalpha(float alpha){
        this.alpha = alpha;
    }
    public void setbeta(float beta){
        this.beta = beta;
    }
    public void setdelta(float delta){
        this.delta = delta;
    }
    public void seteta(float eta){
        this.eta = eta;
    }
    public void setrho(float rho){
        this.rho = rho;
    }
    public void setgamma(float gamma){
        this.gamma = gamma;
    }
    public void setnu(float nu){
        this.nu = nu;
    }
    public void settau(float tau){
        this.tau = tau;
    }
    public int getNodeNumber(){
        return NodeNumber;
    }
    public int getmaxWeight(){
        return maxWeight;
    }
    public int getNodeInit(){
        return NodeInit;
    }
    public float getaplha(){
        return alpha;
    }
    public float getbeta(){
        return beta;
    }
    public float getdelta(){
        return delta;
    }
    public float geteta(){
        return eta;
    }
    public float getrho(){
        return rho;
    }
    public float getgamma(){
        return gamma;
    }
    public float getnu(){
        return nu;
    }
    public float gettau(){
        return tau;
    } 
    public void print(){
        System.out.println("NodeNumber: " + NodeNumber);
        System.out.println("maxweight: " + maxWeight);
        System.out.println("NodeInit: " + NodeInit);
        System.out.println("alpha: " + alpha);
        System.out.println("beta: " + beta);
        System.out.println("delta: " + delta);
        System.out.println("eta: " + eta);
        System.out.println("rho: " + rho);
        System.out.println("gamma: " + gamma);
        System.out.println("nu: " + nu);
        System.out.println("tau: " + tau);
    }

}

class Interface {
    
    public static void main(String[] args) {
        if (args.length == 2)
            withFile(args);
        else if (args.length == 12)
            withInput(args);
        else
            System.out.println("Incorrect user input.");
    }

    public static void withFile(String[] args) {
        Constant programmConstant = new Constant();
        WeightedGraph graphPesoAnt;
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
                int i = 0;
                int j = 0;
                //Copiar matriz
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    part = line.split(" ");
                    for(j = 0; j < graphPesoAnt.getNodeNumber(); j++){
                        graphPesoAnt.addEdge(i, j, Integer.parseInt(part[j]));
                    }
                    i++;
                    System.out.println(line);
                }
                graphPesoAnt.showMatrix();
                //System.out.println("Peso total do grafo: " + graphPesoAnt.totalWeightGraph());
                scanner.close();

                ACOAnt AntColony = new ACOAnt(graphPesoAnt.getNodeNumber(), 2);
                AntColony.move(graphPesoAnt);
                System.out.println("Melhor caminho: ");
         
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            }
        }

        System.out.println("Arguments are " + args[0] + " " + args[1]);
    }

    public static void withInput(String[] args) {
        if (!args[0].equals("-r")){
                System.out.println("Incorrect indicator, should be -r.");
                System.out.println("Args[0]: " + args[0]);            
            }
        else{
            Constant programmConstant = new Constant();
            WeightedGraph graphPesoAnt;
            FeromonasGraph feromonasGraph;

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

            graphPesoAnt.createRandomMatrix(graphPesoAnt,feromonasGraph);
            graphPesoAnt.showMatrix();
            feromonasGraph.showMatrix();

        }
        System.out.println("Arguments are " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " +
                args[4] + " " + args[5] + " " + args[6] + " " + args[7] + " " + args[8] + " " +
                args[9] + " " + args[10] + " " + args[11]);
        
    
        
    }

}


/*class ArgumentPrinter {
    public void printArguments(String flag, int n, String a, int n1, String alpha, String beta, String delta, String eta, String rho) {
        System.out.println("Flag: " + flag);
        System.out.println("n: " + n);
        System.out.println("a: " + a);
        System.out.println("n1: " + n1);
        System.out.println("Alpha: " + alpha);
        System.out.println("Beta: " + beta);
        System.out.println("Delta: " + delta);
        System.out.println("Eta: " + eta);
        System.out.println("Rho: " + rho);
    }

    public static void boogie(String[] args) {
        System.out.println("Good Morning USA!!!");
        System.out.println(args.length);
        if (args.length < 9) {
            System.out.println("Insufficient arguments provided.");
            return;
        }
        
        String flag = args[0];
        int n = Integer.parseInt(args[1]);
        String a = args[2];
        int n1 = Integer.parseInt(args[3]);
        String alpha = args[4];
        String beta = args[5];
        String delta = args[6];
        String eta = args[7];
        String rho = args[8];
        
        ArgumentPrinter printer = new ArgumentPrinter();
        printer.printArguments(flag, n, a, n1, alpha, beta, delta, eta, rho);
        System.out.println("Hello World");
    }
}*/







