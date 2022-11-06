
public class PoisonPill extends Task{

    public PoisonPill(int[][] rango, int i, int j) {
        super(rango, i, j);
    }

    public void run(){
        throw (new RuntimeException("Poison Exception"));
    }
} 
