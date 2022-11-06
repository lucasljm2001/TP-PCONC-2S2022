
public class PoisonPill implements Runnable{

    public void run(){
        throw (new RuntimeException("Poison Exception"));
    }
} 
