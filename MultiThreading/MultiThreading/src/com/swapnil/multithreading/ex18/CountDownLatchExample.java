package com.swapnil.multithreading.ex18;

import java.util.concurrent.*;

public class CountDownLatchExample {

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//        Future<String> submit1 = executorService.submit(new Task());
//        Future<String> submit2 = executorService.submit(new Task());
//        Future<String> submit3 = executorService.submit(new Task());
//
//        try {
//            System.out.println( submit1.get());
//            System.out.println( submit2.get());
//            System.out.println( submit3.get());
//
//           ;
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("This task need to be done only after above tasks completed");


//        int numberOfServices = 3;
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        CountDownLatch countDownLatch = new CountDownLatch(numberOfServices);     //This will create count down of number of task provided, once its done(becomes  0) then main thread start execution further. once countDownLatch becomes zero it cannot be reset(ie it cannot be reused)
//
//        Future<String> submit1 = executorService.submit(new Task1(countDownLatch));
//        Future<String> submit2 = executorService.submit(new Task1(countDownLatch));
//        Future<String> submit3 = executorService.submit(new Task1(countDownLatch));
//        try {
//            countDownLatch.await();                     //main thread will wait here to complete all task
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("This task need to be done only after above tasks completed");



        int numberOfServices = 3;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfServices);     //This will create count down of number of task provided, once its done(becomes  0) then main thread start execution further

        for (int i = 0; i < numberOfServices; i++) {
            new Thread(new Task2(countDownLatch)).start();
        }

        try {
            countDownLatch.await(3, TimeUnit.SECONDS);                     //main thread will wait here for 3 sec, after that it will continue further
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("This task need to be done only after above tasks completed");


    }
}


class Task1 implements Callable<String> {

    private final CountDownLatch latch;

    public Task1(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        try{
            System.out.println(Thread.currentThread().getName() + " Service started");
            Thread.sleep(2000);
        }finally {
            latch.countDown();
        }
        return "ok";
    }
}


class Task2 implements Runnable {

    private final CountDownLatch latch;

    public Task2(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(6000);
            System.out.println(Thread.currentThread().getName() + " Service started");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            latch.countDown();
        }

    }
}

