package com.swapnil.multithreading.ex17;

import java.util.concurrent.*;

public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
  /*      ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(()->{
            System.out.println("Task executed after 5 seconds.");
        }, 5, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(()->{
            System.out.println("Task executed after 10 seconds, every 5 seconds.");
        }, 10, 5, TimeUnit.SECONDS);

        executorService.shutdown();  */             // Here only first task get executed, because shutdown don't wait for periodic task(ie scheduleAtFixedRate) as it continues to run



    /*    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(()->{
            System.out.println("Task executed after 5 seconds.");
        }, 5, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(()->{                           //This will execute given task after every 5 seconds, no matters whether that task taking more than 5 sec
                                                                            //ie after each 5 sec that task start execution no matter whether first completed or not
            System.out.println("Task executed after 10 seconds, every 5 seconds.");
        }, 10, 5, TimeUnit.SECONDS);

        executorService.schedule(()->{executorService.shutdown();}, 30, TimeUnit.SECONDS);     */    //Here we run another task to shutdown after 30 sec, to handle above case



        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.schedule(()->{
            System.out.println("Task executed after 5 seconds.");
        }, 5, TimeUnit.SECONDS);

        ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(() -> {                           //This will execute given task after 5 seconds of completion of previous task,
            //ie after completion of first task - wait 5 sec - seconds task completion - wait 5 sec, so on.           //This will return  ScheduledFuture<?> scheduledFuture instead Future as in ExecutorService
            System.out.println("Task executed after 10 seconds, every 5 seconds.");
        }, 10, 5, TimeUnit.SECONDS);

        try {
            scheduledFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.schedule(()->{executorService.shutdown();}, 30, TimeUnit.SECONDS);

        ExecutorService executorServiceCatched = Executors.newCachedThreadPool();    //Read method

    }


}
