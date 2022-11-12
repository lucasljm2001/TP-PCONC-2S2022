import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Task implements Runnable{
    ArrayList<int[][]> rangoPorCanal;
    int i;
    int j;
    int valor = 0;
    WritableRaster inputRaster;
    WritableRaster outputRaster;

    int[][] filtro = {{0,1,2},{2,2,0},{0,1,2}};
    public Task(ArrayList<int[][]> rango, int i, int j, WritableRaster inRaster, WritableRaster outRaster){
        this.rangoPorCanal = rango;
        this.i = i;
        this.j = j;
        this.inputRaster = inRaster;
        this.outputRaster = outRaster;
    }
    public ArrayList<int[][]> getRango(){
        return this.rangoPorCanal;
    }
    public int getI(){
        return this.i;
    }
    public int getJ(){
        return this.j;
    }
    public int getValor(){
        if (this.valor > 255){
            return 255;
        }
        else if (this.valor < 0) {
            return 0;
        } else {
            return this.valor;
        }
    }

    public void run(){
        for (int k=0; k<inputRaster.getNumBands(); k++){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    valor += rangoPorCanal.get(k)[i][j] * filtro[i][j];
                }
            }
            outputRaster.setSample(this.i,this.j,k,valor);
        }
    }
}
