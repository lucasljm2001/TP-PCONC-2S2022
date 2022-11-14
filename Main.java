import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Config config = new Config();
        config.inputInFilename(scanner);
        config.inputOutputFilename(scanner);
        config.inputBufferSize(scanner);
        config.inputFilterWorkerAmount(scanner);
        config.inputFilterName(scanner);


        File imgInput = new File (config.getInputFilename());
        BufferedImage bi = ImageIO.read(imgInput);
        WritableRaster inputRaster = bi.getRaster();

        int ancho = inputRaster.getWidth();
        int alto = inputRaster.getHeight();

        WritableRaster outputRaster = inputRaster.createCompatibleWritableRaster(ancho-2, alto-2);

        Timer timer = new Timer();
        timer.startRunning();
        ArrayList<Task> tasks = new ArrayList<Task>();
        for(int i=0; i<ancho-2; i++){
            for(int j=0; j<alto-2; j++){
                tasks.add(new Task(dividirMatriz(inputRaster, i, j), i, j, inputRaster, outputRaster, config.getFilterName()));
            }
        }

        Buffer buffer = new Buffer(config.getBufferSize()); 
        WorkerCounter workerCounter = new WorkerCounter();
        int threads = config.getFilterWorkerAmount(); 
        ThreadPool pool = new ThreadPool(threads, buffer, workerCounter);
        pool.iniciar();
        for (Task task : tasks){
            pool.launch(task);
        }
        pool.loadFinishTaks();
        workerCounter.terminarEjecucion();

        BufferedImage bi_salida = new BufferedImage (bi.getColorModel() , outputRaster , bi.isAlphaPremultiplied () , null);
        File outputfile = new File (config.getOutputFilename());
        ImageIO.write(bi_salida, "jpg" , outputfile );
        timer.stopRunning();
        timer.printTime();
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
