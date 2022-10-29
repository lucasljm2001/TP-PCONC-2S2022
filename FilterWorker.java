public class FilterWorker extends Thread {
    Buffer buffer;

    public FilterWorker(Buffer buffer){
        this.buffer = buffer;
    }

    synchronized public void filtrar() throws InterruptedException{
        this.filtrado(buffer.read());
    }
    
    synchronized public void filtrado(Object img){

    }
}
