public class FilterWorker extends Thread {
    Buffer buffer;

    public FilterWorker(Buffer buffer){
        this.buffer = buffer;
    }

    public void run(){
        while(true){
            try {
                Runnable task = buffer.read();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
