public class FilterWorker extends Thread {
    Buffer buffer;

    public FilterWorker(Buffer buffer){
        this.buffer = buffer;
    }

    synchronized public void filtrar() throws InterruptedException{
        int[][] test = {{}};
        this.filtrado(buffer.read(), test, test, 5);
    }
    
    synchronized public void filtrado(Object img, int[][] filtro, int[][] imagen, int ancho){
        // int[][] filtro = {{0,1,2},{2,2,0},{0,1,2}};
        // int[][] imagen = {{3,3,2,1,0},{0,0,1,3,1},{3,1,2,2,3},{2,0,0,2,2},{2,0,0,0,1}};
        int[][] nmatriz = new int[3][3];

        for(int i=0; i<ancho-2; i++){ // Cuando se le pasa el valor a esta funcion, debe ser dos menos
            for(int k=0; k<ancho-2; k++){
                nmatriz[i][k] = convertir(filtro, imagen, i, k, ancho);
                System.out.println(nmatriz[i][k]);
            }
            System.out.println("terminoFila");
        }
    }

    public int convertir(int[][] filtro, int[][] imagen, int ni, int nk, int ancho){
        int valor = 0;
        for(int i=ni; i<ancho-2+ni; i++){
            for(int k=nk; k<ancho-2+nk; k++){
                valor += imagen[i][k] * filtro[i-ni][k-nk];
            }
        }
        return valor;
    }
}
