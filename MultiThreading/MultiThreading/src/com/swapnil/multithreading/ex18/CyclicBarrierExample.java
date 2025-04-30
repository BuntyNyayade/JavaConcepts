package com.swapnil.multithreading.ex18;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args)  {
        int numberOfSubsystems = 4;
   //     CyclicBarrier barrier = new CyclicBarrier(numberOfSubsystems);
        CyclicBarrier barrier = new CyclicBarrier(numberOfSubsystems, new Runnable() {         //Here run method will execute when all threads reach at barrier, performed by last thread
            @Override
            public void run() {
                System.out.println("All subsystems are up and running. System startup complete.");
            }
        });

        Thread webServerThread = new Thread(new Subsystem("Web Server", 2000, barrier));
        Thread databaseThread = new Thread(new Subsystem("Database", 4000, barrier));
        Thread cacheThread = new Thread(new Subsystem("Cache", 3000, barrier));
        Thread messagingServiceThread = new Thread(new Subsystem("Messaging Service", 3500, barrier));

        webServerThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();

//
//        System.out.println("Number of parties: " +  barrier.getParties());          //return no of parties(i.e 4)
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Number of waiting: " +  barrier.getNumberWaiting());
//        barrier.reset();                                                           //After reset(), the barrier is like new.Threads that were already waiting get exceptions.You can start using await() again with new threads.


    }


}

class Subsystem implements Runnable {
    private String name;
    private int initializationTime;
    private CyclicBarrier barrier;

    public Subsystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " initialization started.");
            Thread.sleep(initializationTime); // Simulate time taken to initialize
            System.out.println(name + " initialization complete.");
            barrier.await();                                   // Suppose first thread reach at this point, then it will wait for other threads to reach here(it will maintain count like how many times await() called, here it will be 4)
                                                               // main thread will not wait it will continue further execution
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}