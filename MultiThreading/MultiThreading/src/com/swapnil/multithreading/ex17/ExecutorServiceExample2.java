package com.swapnil.multithreading.ex17;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample2 {

    public static void main(String[] args) {
        ExecutorService executorSer = Executors.newFixedThreadPool(2);

        Callable<Integer> callable1 = () ->{
            System.out.println("Task1");
            Thread.sleep(1000);
            return 1;
        };

        Callable<Integer> callable2 = () ->{
            Thread.sleep(1000);
            System.out.println("Task2");
            return 2;
        };

        Callable<Integer> callable3 = () ->{
            Thread.sleep(1000);
            System.out.println("Task3");
            return 3;
        };

        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);


        //This will return result of any task which executed first completely,  Upon normal or exceptional return, tasks that have not completed are cancelled.

        {
            try {
                Integer i = executorSer.invokeAny(list);
                System.out.println(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorSer.shutdown();




        ExecutorService executorService = Executors.newFixedThreadPool(2);


        final Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(2000);
            System.out.println("Task4");
            return 1;
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        future.cancel(false);  //if its true then future is cancelled no matter whether its executing or not
                                                //if its false then if future not started execution then it get cancelled, and if started execution it will not interrupte.
        System.out.println(future.isCancelled());
        System.out.println(future.isDone());
        executorService.shutdown();

    }


}
