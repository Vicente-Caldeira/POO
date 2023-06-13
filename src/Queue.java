import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Queue{
    private PriorityQueue<Event> events;

    Queue(){
        this.events = new PriorityQueue<Event>();
    }

    public void add(Event event){
        events.add(event);
    }
    public Event next(Event event){
        return events.poll();
    }
    public int size(){
        return events.size();
    }
       
}

class Event implements Comparable<Event> {
    public double time;
    public Runnable action;

    public Event(double time, Runnable action) {
        this.time = time;
        this.action = action;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}

public class Colony {
    private List<ACOAnt> ants;
    private PriorityQueue<Event> eventQueue;

    Colony() {
        this.ants = new ArrayList<>();
        this.eventQueue = new PriorityQueue<>();
    }
    // the rest of your code
}

public void moveAnts(WeightedGraph graph,FeromonasGraph feromonas, Constant file) {
    int i=0;
    for (ACOAnt ant : ants) {
        i++;
        System.out.println("Ant: " + i);
        ant.move(graph,feromonas,file);
        feromonas.showMatrix();
        // Assuming time is an appropriate variable that you've defined
        double time = /*appropriate value*/;
        eventQueue.add(new Event(time, () -> {
            FeromonasCycle updateFeromonas = new FeromonasCycle(graph.totalWeightGraph(), count);
            updateFeromonas.updateFeromonas(file, feromonas, ant.getCurrentPath());
        }));
    }
}

public void runEvents() {
    while (!eventQueue.isEmpty()) {
        Event event = eventQueue.poll();
        event.action.run();
    }
}


public class Simulation {
	// aqui é onde a magia acontece

	Pheromones phero;
	EventList simqueue;
	Aco context;

	public Simulation(ACOAnt ant, Graph graph) {
		this.context = _context;
		phero = new Pheromones(context.getEta(),context.getRho(),context.getPheromoneLevel(),graph.getEdges());
		simqueue = new EventList();

	}

	public void run(Graph graph) {
		for(Ant ant: context.getAnts()) {
			simqueue.add(new Move(0.0, ant, phero));
		}
		//cycle 19 times
		printParameters(context,graph);
		for (int i = 1; i < 20; i++) {
			simqueue.add(new Observation(i * context.final_time/20,i,context));
		}
		simqueue.add(new End(context.getFinalTime(),context));  // end of simulation
		while (simqueue.size() > 0) {
			Event next = simqueue.removeFirst();
			next.execute(simqueue);

		}
	}

	public void printParameters(Aco context, Graph graph) {
		System.out.println("Parameters:");
		System.out.println("Number of nodes:" + context.getNumNodes());
		System.out.println("Nest node:" + context.getNestNode());
		System.out.println("Colony size:" + context.getColonySize());
		System.out.println("Alpha:" + context.getAlpha());
		System.out.println("Beta:" + context.getBeta());
		System.out.println("Delta:" + context.getDelta());
		System.out.println("Eta:" + context.getEta());
		System.out.println("Rho:" + context.getRho());
		System.out.println("Pheromone level:" + context.getPheromoneLevel());
		System.out.println("Final time:" + context.getFinalTime());
		System.out.println("with graph:");
		graph.printGraph();
	}
	public void end(Aco context) {
	}

	public void checkpoint(Aco context, Double time) {
		System.out.println("checkpoint:");
		System.out.println("present instant:" + time);
		System.out.println("Top candidate cycles:");
		System.out.println("Number of move events:" + context.getNumMovs());
		System.out.println("Best Hamiltonian cycle: :");
	}
}

public class EventList {
    // the priority queue
    private final PriorityQueue<Event> events;

    // constructor
    public EventList() {
        events = new PriorityQueue<Event>(Event::compareTo);
    }

    // add an event to the list
    public void add(Event _event) {
        events.add(_event);
    }

    // get the next event
    public IEvent next() {
        return events.poll();
    }

    // get the size of the list
    public int size() {
        return events.size();
    }

    public void clear() {
        events.clear();
    }

    // remove first
    public Event removeFirst() {
        return events.poll();
    }
}