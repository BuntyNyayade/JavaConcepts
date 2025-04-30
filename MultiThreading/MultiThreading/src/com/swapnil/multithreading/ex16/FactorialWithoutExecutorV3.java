package com.swapnil.multithreading.ex16;

public class FactorialWithoutExecutorV3 {

    public int factorial(int n) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (n == 0 || n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        FactorialWithoutExecutorV3 factorialWithoutExecutor = new FactorialWithoutExecutorV3();
        long startTime = System.currentTimeMillis();
        Thread[] threads = new  Thread[9];

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            threads[i-1] = new Thread(()->{System.out.println(factorialWithoutExecutor.factorial(finalI));});

            threads[i-1].start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Total time taken: " + (endTime - startTime));
    }
}
