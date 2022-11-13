import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws Exception {
        File imgInput = new File ("entrada.jpg");
        BufferedImage bi = ImageIO.read(imgInput);
        WritableRaster inputRaster = bi.getRaster();

        int ancho = inputRaster.getWidth();
        int alto = inputRaster.getHeight();

        WritableRaster outputRaster = inputRaster.createCompatibleWritableRaster(ancho, alto);

        ArrayList<Task> tasks = new ArrayList<Task>();
        for(int i=0; i<ancho-1; i++){
            for(int j=0; j<alto-1; j++){
                tasks.add(new Task(dividirMatriz(inputRaster, i, j), i, j, inputRaster, outputRaster));
            }
        }

        Buffer buffer = new Buffer(8); //PARAMETRIZAR
        WorkerCounter workerCounter = new WorkerCounter();
        int threads = 4; //PARAMETRIZAR
        ThreadPool pool = new ThreadPool(threads, buffer, workerCounter);
        pool.iniciar();
        for (Task task : tasks){
            pool.launch(task);
        }
        pool.loadFinishTaks();
        workerCounter.terminarEjecucion();

        BufferedImage bi_salida = new BufferedImage (bi.getColorModel() , outputRaster , bi.isAlphaPremultiplied () , null);
        File outputfile = new File ("salida.jpg" );
        ImageIO.write(bi_salida, "jpg" , outputfile );
    }

    public static ArrayList<double[][]> dividirMatriz(WritableRaster inputRaster, int ni, int nj){
        ArrayList<double[][]> nmatriz = new ArrayList<double[][]>();
        for (int k=0; k<inputRaster.getNumBands(); k++){
            double[][] matrix = new double[3][3];
            for(int i=-1; i<2; i++){
                for(int j=-1; j<2; j++){
                    try {
                        matrix[i+1][j+1] = inputRaster.getSample(ni+i, nj+j, k);
                    } catch (IndexOutOfBoundsException ex){
                        matrix[i+1][j+1] = 0;
                    }
                }
            }
            nmatriz.add(matrix);
        }

        return nmatriz;
    }
}
