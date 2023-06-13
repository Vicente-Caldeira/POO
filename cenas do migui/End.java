package pec;

import aco.*;

public class End extends Event {
	Aco context;

	public End(double _time, Aco aco) {
		super(_time);
		context = aco;
	}

	// override execute
	@Override
	public void execute(EventList sim) {
		System.out.println("Final Event");
		System.out.println("\tPresent instant:" + context.getFinalTime());
		System.out.println("Number of move events:" + context.getNumMovs());
		System.out.println("Number of evaporation events:" + context.getNumEvap());
		System.out.println("\tTop candidate cycles:");
		context.printTopCycles();
		System.out.println("Best Hamiltonian cycle:");
		context.printBestCycle();
		System.out.println("End of simulation");
		sim.clear();
	}
}
