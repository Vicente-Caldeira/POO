package pec;

import aco.*;

public class Move extends Event {
	Ant ant;
	Pheromones pheromones;

	public Move(Double _time, Ant _ant, Pheromones _pheromones) {
		super(_time);
		ant = _ant;
		pheromones = _pheromones;
	}

	@Override
	public void execute(EventList sim) {
		double next_time = ant.moveAnt(pheromones, sim, this.time);
		this.schedule_next(sim, new Move(time + next_time, ant, pheromones));
	}
}
