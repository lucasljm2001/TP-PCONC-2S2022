import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Task implements Runnable{
    ArrayList<double[][]> rangoPorCanal;
    int i;
    int j;
    int valor = 0;
    WritableRaster inputRaster;
    WritableRaster outputRaster;

    double[][] filtro = {{0,-1,0},{-1,5,-1},{0,-1,0}};
    public Task(ArrayList<double[][]> rango, int i, int j, WritableRaster inRaster, WritableRaster outRaster){
        this.rangoPorCanal = rango;
        this.i = i;
        this.j = j;
        this.inputRaster = inRaster;
        this.outputRaster = outRaster;
    }
    public ArrayList<double[][]> getRango(){
        return this.rangoPorCanal;
    }
    public int getI(){
        return this.i;
    }
    public int getJ(){
        return this.j;
    }
    public double getValor(double valor){
        if (valor > 255){
            return 255;
        }
        else if (valor < 0) {
            return 0;
        } else {
            return valor;
        }
    }

    public void run(){
        for (int k=0; k<inputRaster.getNumBands(); k++){
            double valor = 0;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    valor += rangoPorCanal.get(k)[i][j] * filtro[i][j];
                }
            }
            try {
                outputRaster.setSample(this.i,this.j,k, getValor(valor));
            }
            catch(Exception ex) {
                throw ex;
            }
        }
    }
}
