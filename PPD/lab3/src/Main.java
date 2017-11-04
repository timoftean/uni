import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    static final Random r = new Random();
    static int rows1,columns1,rows2,columns2;
    static long startTime,stopTime,execTime;
    static int[][] matrix1= new int[rows1][columns1];
    static int[][] matrix2= new int[rows2][columns2];
    static int[][] matrix3 =new int[2000][2000];


    public static void initialize() {
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {

                matrix3[i][j]=0;
            }
        }
    }


    public static int[][] generateMatrix(int r1,int col1){
        int[][] x= new int[r1][col1];
        for(int i=0;i<r1;i++){
            for(int j=0;j<col1;j++){
//                Random rand = new Random();

                x[i][j] = r.nextInt(5) + 1;
            }
        }
        return x;

    }

    public static void printMatrix() {
        System.out.println("Printez matricea finala");
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                System.out.println(matrix3[i][j] + " ");
                //TODO: timp de executie
            }
        }

    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter nr of rows for the first matrix");
        rows1 = keyboard.nextInt();
        System.out.println("enter nr of columns for the first matrix");
        columns1 = keyboard.nextInt();

        System.out.println("enter nr of rows for the second matrix");
        rows2 = keyboard.nextInt();
        System.out.println("enter nr of columns for the second matrix");
        columns2 = keyboard.nextInt();
        System.out.println("enter nr of threads");
        int k = keyboard.nextInt();
        initialize();

        ExecutorService executor = Executors.newFixedThreadPool(k);

        matrix1 = generateMatrix(rows1, columns1);

        matrix2 = generateMatrix(rows2, columns2);

        List<Future<Void>> list=new ArrayList<Future<Void>>();

        if(k > rows1){
            k = 14;
        }
        if(k<1){
            k = 1;
        }

        int pass = rows1 / k;
        int rest = rows1 % k;
        int start = 0 ;
        int end = 0;
        int r=0;
        for(int i = 0 ; i < k ; i++){
            System.out.println("start"+start);
            if(rest>0){
                r = 1;
                rest--;
            }else {
                r = 0;
            }
            end = start + pass + r;
            final int c1 = start;
            final int c2 = end-1;
            start = end;
            System.out.println("end"+end);

            list.add(executor.submit(new Task(rows1,columns1,rows2,columns2,matrix1,matrix2,matrix3,c1,c2)));
        }
        startTime = System.currentTimeMillis();

        for (Future<Void> t:list) {
            try {
                t.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


        executor.shutdown();

        try{
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Error" + e.getMessage());
        }

        System.out.println("Finished all threads");

        stopTime = System.currentTimeMillis();
        execTime = stopTime - startTime;
        System.out.println("Time of execution: " + execTime);
    }
}
