/**
 * ThreadPool
 */
public class ThreadPool {

    int cantidad;
    Buffer buffer;
    FilterWorker[] workers;

    public ThreadPool(int cantidad, Buffer buffer){
        this.cantidad = cantidad;
        this.buffer = buffer;
    }

    public void iniciar(){
        for (int i = 0; i < cantidad; i++) {
            workers[i] = new FilterWorker(this.buffer);
        }
    }
}