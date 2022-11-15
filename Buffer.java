
public class Buffer {
    int N;
    Runnable [] data;
    public Buffer(int N){
        this.N = N;
        this.data = new Runnable [N + 1];
    }

    private int begin = 0, end = 0;
    synchronized void write (Runnable o) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        data [begin] = o;
        begin = next(begin);
        notifyAll();
    }

    synchronized Runnable read() throws InterruptedException {
        while (isEmpty()) { 
            wait(); 
        }
        Runnable result = data[end];
        end = next(end);
        notifyAll();
        return result ;
    }
    private boolean isEmpty() { return begin == end ; }
    private boolean isFull() { return next ( begin ) == end ; }
    private int next(int i) { return (i +1) % (N +1); }
}
