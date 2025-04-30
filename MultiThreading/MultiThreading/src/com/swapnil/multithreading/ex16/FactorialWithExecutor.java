package com.swapnil.multithreading.ex16;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FactorialWithExecutor {

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
        FactorialWithExecutor factorialWithoutExecutor = new FactorialWithExecutor();
        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(3);        //Here this 3 thread are reused to perform operation rather than creating new thread
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executorService.submit(()->{System.out.println(factorialWithoutExecutor.factorial(finalI));});

        }

        executorService.shutdown();         //If we don't shutdown application continue to run.
                                            //This method does not wait for previously submitted tasks to complete execution
                                            //i.e main thread will not stop to complete all task
                                            //If we try to submit after shutdown it will give runtime exception

        //  executorService.submit(()->{System.out.println(factorialWithoutExecutor.factorial(2));});

        //  executorService.shutdownNow();      //This will immediately shutdown no matters whether task executed or not

        try {
//           boolean val =  executorService.awaitTermination(5000
//                   , TimeUnit.MILLISECONDS);                               //Blocks until all tasks have completed execution after a shutdown request, if tasks not completed within given time it will return false.
//            System.out.println(val);

            while (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)){
                System.out.println("Waiting..");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Total time taken: " + (endTime - startTime));
    }
}
