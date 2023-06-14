public class EventMove extends Event{
    ACOAnt ant;
    FeromonasGraph feromonasGraph;
    Constant file;
    Observation fileObservation;

    public EventMove(Double time,ACOAnt ant,FeromonasGraph feromonasGraph, Constant file, Observation fileObservation){
        super(time);
        this.ant = ant;
        this.feromonasGraph = feromonasGraph;
        this.file = file;
        this.fileObservation = fileObservation;
    }
    @Override
    public void runEvent(Queue list){
        
        double execute_time = ant.move(list,feromonasGraph,file,time);
        fileObservation.updateMove();
        this.nextEvent(new EventMove(this.time + execute_time, ant, feromonasGraph, file, fileObservation), list);
    }
}