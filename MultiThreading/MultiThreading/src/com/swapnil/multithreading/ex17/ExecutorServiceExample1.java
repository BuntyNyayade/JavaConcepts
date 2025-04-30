package com.swapnil.multithreading.ex17;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceExample1 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);        //Here this 3 thread are reused to perform operation rather than creating new thread

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };

        executorService.submit(runnable);

        Future<?> submitRun = executorService.submit(() -> {       //This is lambda version of above Runnable interface, which will not return any value
            System.out.println("Hello World");
        });

        System.out.println(submitRun.get());                    //This get() method will wait until task is executed completely as it return value

        submitRun.isDone();                                       //Check if task is completed or not

        submitRun.isCancelled();





        Future<?> submitRunDef = executorService.submit(() -> {       //This submitRunDef will return value mentioned when we call get() method
            try {
                Thread.sleep(2000);
                System.out.println("Hello World");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "Done");

        try {
            System.out.println(submitRunDef.get(1, TimeUnit.SECONDS)); //This get will wait for given time if didn't get result, throw Exception
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println(executorService.isShutdown());






        Callable callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    return "Hello World";
                }
           };

        executorService.submit(callable);

        Future<String> submitCall = executorService.submit(() -> "Hello World");   //This is lambda version of above Callable interface, which will return any value

        System.out.println(submitCall.get());





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

       List<Future<Integer>> futures = executorService.invokeAll(list);   //This will take all task simultaneously, and wait until all task completed

        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }




        executorService.shutdown();
        //Thread.sleep(10);
        System.out.println(executorService.isTerminated()); // This will return false, because after shutdown it get called immediately so remove above comment and try



        ExecutorService executorServ = Executors.newFixedThreadPool(2);

        try {
            List<Future<Integer>> futures1 = executorServ.invokeAll(list, 1, TimeUnit.SECONDS);    //This will wait for 1 Sec to complete tasks, after that whatever pending keep them as it is
                                                                                                           //After 1 sec only 2 task are completed as we have 2 threads
            for (Future<Integer> integerFuture : futures1) {
                System.out.println(integerFuture.get());
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

        executorServ.shutdown();

    }
}
