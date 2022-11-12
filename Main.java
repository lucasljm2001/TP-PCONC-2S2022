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
        int canales = inputRaster.getNumBands();

        WritableRaster outputRaster = inputRaster.createCompatibleWritableRaster(ancho, alto);

        int cantTasks = (ancho - 2) * (alto - 2);

        // raster2.setPixels(0 , 0 , ancho , alto ,
        // new double[ ancho * alto * canales ]);
        // BufferedImage bi_salida = new BufferedImage (
        // bi.getColorModel() , raster2 , bi.isAlphaPremultiplied () , null);
        // File outputfile = new File ("salida.jpg" );
        // ImageIO.write(bi_salida, "jpg" , outputfile );
        
        Task[] tasks = new Task[(ancho - 2) * (alto - 2)];
        int counter = 0;
        for (int k = 0; k<canales; k++){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){ // el 3 es el (ancho - 2)
                    tasks[counter] = new Task(dividirMatriz(inputRaster, i, j), i, j, inputRaster, outputRaster);
                    counter++;
                }
            }
        }

        Buffer buffer = new Buffer(8);
        WorkerCounter workerCounter = new WorkerCounter();
        int threads = 4; // no se como hacer para pasar datos por linea de comandos
        ThreadPool pool = new ThreadPool(threads, buffer, workerCounter);
        pool.iniciar();
        for (Task task : tasks){
            pool.launch(task);
        }
        pool.loadFinishTaks();
        workerCounter.terminarEjecucion();
        pool.mostrarResultados();
    }

    public static ArrayList<int[][]> dividirMatriz(WritableRaster inputRaster, int ni, int nj){
        ArrayList<int[][]> nmatriz = new ArrayList<int[][]>();
        for (int k=0; k<inputRaster.getNumBands(); k++){
            int[][] matrix = new int[3][3];
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
