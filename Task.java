public class Task implements Runnable{
    int[][] rango;
    int i;
    int j;
    int valor = 0;

    int[][] filtro = {{0,1,2},{2,2,0},{0,1,2}};
    public Task(int[][] rango, int i, int j){
        this.rango = rango;
        this. i = i;
        this.j = j;
    }
    public int[][] getRango(){
        return this.rango;
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
        else {
            return this.valor;
        }
    }

    public void run(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                valor += rango[i][j] * filtro[i][j];
            }
        }
    }
}
