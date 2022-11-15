public class ThreadPool {
    int cantidad;
    Buffer buffer;
    FilterWorker[] workers;
    WorkerCounter workerCounter;

    public ThreadPool(int cantidad, Buffer buffer, WorkerCounter workerCounter){
        this.cantidad = cantidad;
        this.buffer = buffer;
        this.workers = new FilterWorker[cantidad];
        this.workerCounter = workerCounter;
    }

    public void iniciar(){
        for (int i = 0; i < this.cantidad; i++) {
            workers[i] = new FilterWorker(this.buffer);
            workers[i].start();
            workerCounter.sumarThread();
        }
    }

    synchronized public void launch(Task task) throws InterruptedException{
        buffer.write(task);
    }

    synchronized public void loadFinishTaks() throws InterruptedException{
        for (int i=0; i < this.workers.length; i++) {
            buffer.write(new PoisonPill(workerCounter));
        }
    }
}