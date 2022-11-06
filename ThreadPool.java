import java.util.ArrayList;

/**
 * ThreadPool
 */
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

    synchronized public void stop() throws InterruptedException{
        for (int i=0; i < this.workers.length; i++) {
            int[][] rango = new int[1][1];
            buffer.write(new PoisonPill(rango, 0, 0));
            workerCounter.restarThread();
        }
        workerCounter.terminarEjecucion();
    }

    public void mostrarResultados(){
        int[][] resultado = new int[3][3];

        for(FilterWorker worker: workers){
            ArrayList<ArrayList<Integer>> procesosLeidos = worker.getProcesosLeidos();

            for (int i=0; i < procesosLeidos.size(); i++){
                Integer fila = procesosLeidos.get(i).get(1);
                Integer columna = procesosLeidos.get(i).get(2);
                int valueConvert = procesosLeidos.get(i).get(0);

                resultado[fila][columna] = valueConvert;
            }
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){ // el 3 es el (ancho - 2)
                System.out.println(resultado[i][j]);
            }
            System.out.println("Termino fila");
        }
        System.out.println("Termino resultado");
    }
}