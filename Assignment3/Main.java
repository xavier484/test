import java.util.Random;

class Multiplier extends Thread {    
    private int a[][], b[][];    
    private int i;    
    private int j;    
    private volatile int value = 0;    
    
    public Multiplier(int a[][], int b[][], int i, int j) {        
        this.a = a;        
        this.b = b;        
        this.i = i;        
        this.j = j;    
        
    }    

    @Override    
    public void run() {        
        int n = b.length;        
        for (int k = 0; k < n; k++) {            
            value += a[i][k] * b[k][j];        
            
        }    
        
    }    
    
    public int getValue() {        
        return value;    
        
    }
    
}

public class Main {    
    
    public static void initialize(int mat[][]) {        
        // to get new random number        
        Random r = new Random();        
        for (int i = 0; i < mat.length; i++) {            
            for (int j = 0; j < mat[i].length; j++) {                
                mat[i][j] = Math.abs(r.nextInt()) % 100;            
                
            }        
            
        }    
        
    }    
    
    public static void print(int mat[][]) {        
        for (int i = 0; i < mat.length; i++) {            
            for (int j = 0; j < mat[i].length; j++) {                
                System.out.printf("%10d", mat[i][j]);            
                
            }            
            System.out.println();        
            
        }    
        
    }    
    
    public static void main(String[] args) {        
        int m = 2;        
        int n = 3;        
        int p = 4;        
        int a[][] = new int[m][n];        
        int b[][] = new int[n][p];        
        int product[][] = new int[m][p];        
        initialize(a);        
        initialize(b);        
        
               
        for (int i = 0; i < m; i++) {            
            for (int j = 0; j < p; j++) {                
                Multiplier t = new Multiplier(a, b, i, j);                
                t.start();                
                try {                    
                    t.join();                    
                    product[i][j] = t.getValue();                
                    
                } 
                catch (InterruptedException e) {                    
                    e.printStackTrace();                
                    
                }            
                
            }        
            
        }        
        System.out.println("Matrix 1: ");        
        print(a);        
        System.out.println("Matrix 2: ");        
        print(b);        
        System.out.println("Product: ");        
        print(product);    
        
    }
    
}
 