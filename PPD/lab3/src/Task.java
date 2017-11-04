import java.security.Timestamp;
import java.util.concurrent.Callable;


public class Task implements Callable<Void> {

    private int columns1,columns2,rows1,rows2,a,b;
    int matrix1[][];
    int matrix2[][];
    int matrix3[][];

    public Task(int r1,int c1,int r2,int c2,int matrix11[][],int matrix12[][],int matrix13[][],int a,int b){

        this.rows1=r1;
        this.columns1=c1;
        this.rows2=r2;
        this.columns2=c2;
        this.matrix1=matrix11;
        this.matrix2=matrix12;
        this.matrix3=matrix13;
        this.a=a;
        this.b=b;

        }

    public Void call(){

        for (int i1 = a; i1 < b; i1++) {
            for (int j1 = 0; j1 < columns2; j1++) {
                for (int k1 = 0; k1 < rows2; k1++) {
                    matrix3[i1][j1] = matrix1[i1][k1] * matrix2[k1][j1];
                }
            }
        }

        return null;
    }
}
