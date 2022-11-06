import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.sql.Time;

import javax.imageio.ImageIO;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // File imagen = new File ("entrada.png");
        // BufferedImage bi = ImageIO.read( imagen );
        // WritableRaster raster = bi.getRaster();
        // int ancho = raster . getWidth ();
        // int alto = raster . getHeight ();
        // int canales = raster . getNumBands ();
        // WritableRaster raster2 = raster.createCompatibleWritableRaster(
        // ancho , alto );
        // raster2.setPixels(0 , 0 , ancho , alto ,
        // new double[ ancho * alto * canales ]);
        // BufferedImage bi_salida = new BufferedImage (
        // bi.getColorModel() , raster2 , bi.isAlphaPremultiplied () , null);
        // File outputfile = new File ("salida.jpg" );
        // ImageIO.write(bi_salida, "jpg" , outputfile );
        

        int[][] imagen = {{3,3,2,1,0},{0,0,1,3,1},{3,1,2,2,3},{2,0,0,2,2},{2,0,0,0,1}};
        Task[] tasks = new Task[9]; // el valor 9 sale del (ancho - 2) * (altura - 2)
        int counter = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){ // el 3 es el (ancho - 2)
                tasks[counter] = new Task(dividirMatriz(imagen, i, j), i, j);
                counter++;
            }
        }

        Buffer buffer = new Buffer(8);
        WorkerCounter workerCounter = new WorkerCounter();
        int threads = 8; // no se como hacer para pasar datos por linea de comandos
        ThreadPool pool = new ThreadPool(threads, buffer, workerCounter);
        pool.iniciar();
        for (Task task : tasks){
            pool.launch(task);
        }
        pool.stop();
        pool.mostrarResultados();
    }

    public static int[][] dividirMatriz(int[][] matriz, int ni, int nj){
        int[][] nmatriz = new int[3][3];
        for(int i=ni; i<3+ni; i++){
            for(int j=nj; j<3+nj; j++){
                nmatriz[i-ni][j-nj] = matriz[i][j];
            }
        }
        return nmatriz;
    }
}
