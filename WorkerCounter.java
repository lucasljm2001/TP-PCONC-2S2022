public class WorkerCounter {
    int threadsProcesando;

    synchronized public void sumarThread(){
        threadsProcesando++;
    }

    synchronized public void terminarEjecucion() throws InterruptedException{
        while(threadsProcesando > 0){
            wait();
        }
        notify();
    }
}
