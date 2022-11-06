public class WorkerCounter {
    int threadsProcesando = 0;

    synchronized public void sumarThread(){
        threadsProcesando++;
    }

    synchronized public void terminarEjecucion() throws InterruptedException{
        while(threadsProcesando > 0){
            wait();
        }
        notify();
    }

    synchronized public void restarThread(){
        threadsProcesando--;
    }
}
