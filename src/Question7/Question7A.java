package Question7;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Question7A {
    private static final int NUM_THREADS = 4; // number of threads to use

    public static void main(String[] args) {
        int n = 3; // size of matrices
        Random random = new Random();

        // create matrices A and B with random values
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = random.nextInt();
                B[i][j] = random.nextInt();
            }
        }

        // create a thread pool with NUM_THREADS threads
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        // create an array of Future objects to hold the results of each thread
        Future<Double>[][] futures = new Future[n][n];

        // multiply the matrices using threads
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final int row = i;
                final int col = j;
                futures[i][j] = pool.submit(() -> {
                    double sum = 0;
                    for (int k = 0; k < n; k++) {
                        sum += A[row][k] * B[k][col];
                    }
                    return sum;
                });
            }
        }

        // wait for all threads to finish and get the results
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    C[i][j] = futures[i][j].get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // shutdown the thread pool
        pool.shutdown();

        // print the result
        System.out.println("Result:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}