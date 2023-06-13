package pec;

import aco.*;

public class Observation extends Event {
  Aco context;
  int number;

  public Observation(double _time, int number, Aco aco) {
    super(_time);
    context = aco;
  }

  // override execute
  @Override
  public void execute(EventList sim) {
    System.out.println("Observation " + this.number);
    System.out.println("\tPresent instant:" + this.time);
    System.out.println("Number of move events:" + context.getNumMovs());
    System.out.println("Number of evaporation events:" + context.getNumEvap());
    System.out.println("\tTop candidate cycles:");
    context.printTopCycles();
    System.out.println("Best Hamiltonian cycle:");
    context.printBestCycle();
  }
}
