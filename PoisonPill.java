
public class PoisonPill implements Runnable{
    private WorkerCounter workerCounter;
    public PoisonPill(WorkerCounter workerCounter){
        this.workerCounter = workerCounter;
    }

    public void run(){
        workerCounter.restarThread();
        throw (new RuntimeException("Poison Exception"));
    }
}
