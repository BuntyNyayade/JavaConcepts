package com.swapnil.multithreading.ex4;

public class MultiThreading extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Thread is running");
        } catch (InterruptedException e) {
            System.out.println("Exception is: " + e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MultiThreading multiThreading = new MultiThreading();
        multiThreading.start();
        multiThreading.interrupt();       //This will stop the execution of thread(ie multiThreading) at whatever point it is

    }
}
