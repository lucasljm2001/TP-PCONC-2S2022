import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File imagen = new File ("entrada.png");
        BufferedImage bi = ImageIO.read( imagen );
        WritableRaster raster = bi.getRaster();
        int ancho = raster . getWidth ();
        int alto = raster . getHeight ();
        int canales = raster . getNumBands ();
        WritableRaster raster2 = raster.createCompatibleWritableRaster(
        ancho , alto );
        raster2.setPixels(0 , 0 , ancho , alto ,
        new double[ ancho * alto * canales ]);
        BufferedImage bi_salida = new BufferedImage (
        bi.getColorModel() , raster2 , bi.isAlphaPremultiplied () , null);
        File outputfile = new File ("salida.jpg" );
        ImageIO.write(bi_salida, "jpg" , outputfile );

        // Buffer buffer = new Buffer(2);
        // int threads = 2; // no se como hacer para pasar datos por linea de comandos
        // ThreadPool pool = new ThreadPool(threads, buffer);
        // pool.iniciar();
    } 
}
