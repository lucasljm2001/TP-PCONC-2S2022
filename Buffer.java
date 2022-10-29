import java.util.Queue;

public class Buffer {
    int capacidad;
    Queue<String> cola;

    public Buffer(int capacidad){
        this.capacidad = capacidad;
    }

    synchronized public void leer() throws InterruptedException{
        while(cola.isEmpty()){
            wait();
        }
        cola.remove();
        notify();
    }

    synchronized public void producir(String producto) throws InterruptedException{
        while(cola.size() == capacidad){
            wait();
        }
        cola.add(producto);
        notify();
    }
}
