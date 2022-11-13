import java.util.ArrayList;

public class FilterWorker extends Thread {
    Buffer buffer;
    ArrayList<ArrayList<Integer>> procesosLeidos = new ArrayList<ArrayList<Integer>>();
    int counter = 0;
    public FilterWorker(Buffer buffer){
        this.buffer = buffer;
    }

    public void run(){
        while(true){
            try {
                Runnable task = buffer.read();
                if (task.getClass() == Task.class){
                    Task t;
                    t  = (Task) task;
                    t.run();
                    ArrayList<Integer> innerArraylist = new ArrayList<Integer>();
                    //innerArraylist.add(Integer.valueOf(t.getValor()));
                    //innerArraylist.add(Integer.valueOf(t.getI()));
                    //innerArraylist.add(Integer.valueOf(t.getJ()));
   
                    procesosLeidos.add(innerArraylist);
                    counter ++;
                }
                else{
                    task.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getProcesosLeidos(){
        return procesosLeidos;
    }


}
