/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(2);
        int threads = 2; // no se como hacer para pasar datos por linea de comandos
        ThreadPool pool = new ThreadPool(threads, buffer);
        pool.iniciar();
    } 
}
