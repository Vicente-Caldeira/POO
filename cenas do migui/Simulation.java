package pec;

import aco.*;
import graph.*;

import java.util.ArrayList;

public class Simulation {
	// aqui é onde a magia acontece

	Pheromones phero;
	EventList simqueue;
	Aco context;

	public Simulation(Aco _context, Graph graph) {
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
