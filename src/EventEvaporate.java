import java.util.Random;

public class EventEvaporate extends Event{
    private int Node1;
    private int Node2;
    private double eta;
    private double rho;
    private FeromonasGraph feromonasgraph;
    private Observation observer;

    EventEvaporate(double time, int Node1, int Node2, double eta, double rho, FeromonasGraph feromonasgraph,Observation observer){
        super(time);
        this.Node1 = Node1;
        this.Node2 = Node2;
        this.eta = eta;
        this.rho = rho;
        this.feromonasgraph = feromonasgraph;
        this.observer = observer;
    }

    private void schedule_next(Queue list, EventEvaporate next) {
		list.add(next);
	}

    @Override
    public void runEvent(Queue list){
        Random random = new Random();
		double randomValue = -(1/eta) * Math.log(1 - random.nextDouble());

        float feromonas = 0;
        feromonas = feromonasgraph.getWeight(Node1, Node2);
        feromonas-=rho;
        feromonasgraph.addEdge(Node1, Node2, feromonas);

        observer.updateEvaporation();

		schedule_next(list, new EventEvaporate(time + randomValue, Node1, Node2, eta,rho, feromonasgraph,observer));
    }
}
