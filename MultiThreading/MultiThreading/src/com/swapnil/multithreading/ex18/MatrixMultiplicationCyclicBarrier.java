package com.swapnil.multithreading.ex18;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MatrixMultiplicationCyclicBarrier {

    private static final int SIZE = 3;
    private static int[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    private static int[][] B = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
    };

    private static int[][] C = new int[SIZE][SIZE];

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(SIZE, new Runnable() {
            @Override
            public void run() {
                System.out.println("All rows computed. Resultant matrix:");
                printMatrix(C);
            }
        });

        for (int i = 0; i < SIZE; i++) {
            Thread worker = new Thread(new Worker(i, barrier));
            worker.start();
        }
    }

    static class Worker implements Runnable {
        private int row;
        private CyclicBarrier barrier;

        public Worker(int row, CyclicBarrier barrier) {
            this.row = row;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    C[row][j] += A[row][k] * B[k][j];
                }
            }
            System.out.println("Row " + row + " computation done.");

            try {
                barrier.await(); // Wait for other threads
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
