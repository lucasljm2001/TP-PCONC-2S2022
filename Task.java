public class Task {
    int[][] rango;
    int i;
    int j;
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
    public int run(){
        int valor = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                valor += rango[i][j] * filtro[i][j];
            }
        }
        return valor;
    }
}
