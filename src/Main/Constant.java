package Main;
import Main.Constant;


/**
 * The Constant class represents the constant values used in the program.
 */

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

    /**
     * Constructs a Constant object with default values.
     */
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

    /**
     * Sets the number of nodes.
     *
     * @param NodeNumber The number of nodes.
     */
    public void setNodeNumber(int NodeNumber){
        this.NodeNumber = NodeNumber;
    }

    /**
     * Sets the maximum weight.
     *
     * @param maxWeight The maximum weight.
     */
    public void setmaxWeight(int maxWeight){
        this.maxWeight = maxWeight;
    }

    /**
     * Sets the initial node.
     *
     * @param NodeInit The initial node.
     */
    public void setNodeInit(int NodeInit){
        this.NodeInit = NodeInit-1;
    }

    /**
     * Sets the alpha value.
     *
     * @param alpha The alpha value.
     */
    public void setalpha(float alpha){
        this.alpha = alpha;
    }

    /**
     * Sets the beta value.
     *
     * @param beta The beta value.
     */
    public void setbeta(float beta){
        this.beta = beta;
    }

    /**
     * Sets the delta value.
     *
     * @param delta The delta value.
     */
    public void setdelta(float delta){
        this.delta = delta;
    }

    /**
     * Sets the eta value.
     *
     * @param eta The eta value.
     */
    public void seteta(float eta){
        this.eta = eta;
    }

    /**
     * Sets the rho value.
     *
     * @param rho The rho value.
     */
    public void setrho(float rho){
        this.rho = rho;
    }

    /**
     * Sets the gamma value.
     *
     * @param gamma The gamma value.
     */
    public void setgamma(float gamma){
        this.gamma = gamma;
    }

    /**
     * Sets the nu value.
     *
     * @param nu The nu value.
     */
    public void setnu(float nu){
        this.nu = nu;
    }

    /**
     * Sets the tau value.
     *
     * @param tau The tau value.
     */
    public void settau(float tau){
        this.tau = tau;
    }

    /**
     * Gets the number of nodes.
     *
     * @return The number of nodes.
     */
    public int getNodeNumber(){
        return NodeNumber;
    }

    /**
     * Gets the maximum weight.
     *
     * @return The maximum weight.
     */
    public int getmaxWeight(){
        return maxWeight;
    }

    /**
     * Gets the initial node.
     *
     * @return The initial node.
     */
    public int getNodeInit(){
        return NodeInit;
    }

    /**
     * Gets the alpha value.
     *
     * @return The alpha value.
     */
    public float getaplha(){
        return alpha;
    }

    /**
     * Gets the beta value.
     *
     * @return The beta value.
     */
    public float getbeta(){
        return beta;
    }

    /**
     * Gets the delta value.
     *
     * @return The delta value.
     */
    public float getdelta(){
        return delta;
    }

    /**
     * Gets the eta value.
     *
     * @return The eta value.
     */
    public float geteta(){
        return eta;
    }

    /**
     * Gets the rho value.
     *
     * @return The rho value.
     */
    public float getrho(){
        return rho;
    }

    /**
     * Gets the gamma value.
     *
     * @return The gamma value.
     */
    public float getgamma(){
        return gamma;
    }

    /**
     * Gets the nu value.
     *
     * @return The nu value.
     */
    public float getnu(){
        return nu;
    }

    /**
     * Gets the tau value.
     *
     * @return The tau value.
     */
    public float gettau(){
        return tau;
    }
    
    /**
     * Prints the values of the constants.
     */
    public void print(){
        System.out.println("Input parameters:");
        System.out.println("n: " + NodeNumber);
        System.out.println("n1: " + NodeInit);
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
