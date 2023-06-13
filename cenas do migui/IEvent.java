package pec;

public interface IEvent
{
    public double getTime();

    public void setTime(double _time);

    public void execute(EventList sim);

    public int compareTo(Event that);
}