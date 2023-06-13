package pec;

abstract class Event implements IEvent {
    double time;

    public Event(double _time) {
        time = _time;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double _time) {
        this.time = _time;
    }

    public void execute(EventList sim) {}
	@Override
    public int compareTo(Event that) {return Double.compare(this.time, that.time);}

    void schedule_next(EventList sim, Event next) {
        sim.add(next);
    }
}
