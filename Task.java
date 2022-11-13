import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class Task implements Runnable{
    ArrayList<double[][]> rangoPorCanal;
    int i;
    int j;

    WritableRaster inputRaster;
    WritableRaster outputRaster;

    double[][] sharpen = {{0,-1,0},{-1,5,-1},{0,-1,0}};
    double[][] blur = {{0.1,0.1,0.1},{0.1,0.1,0.1},{0.1,0.1,0.1}};
    double[][] sobelVertical = {{1,2,1},{0,0,0},{-1,-2,-1}};
    double[][] sobelHorizontal = {{1,0,-1},{2,0,-2},{1,0,-1}};

    double[][] filtro;
    public Task(ArrayList<double[][]> rango, int i, int j, WritableRaster inRaster, WritableRaster outRaster, String filter){
        this.rangoPorCanal = rango;
        this.i = i;
        this.j = j;
        this.inputRaster = inRaster;
        this.outputRaster = outRaster;
        this.filtro = setFiltro(filter);
    }

    private double[][] setFiltro(String filter){
        switch(filter){
            case "blur":
                return blur;
            case "sharpen":
                return sharpen;
            case "sobelvertical":
                return sobelVertical;
            default:
                return sobelHorizontal;
        }
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
