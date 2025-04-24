package com.swapnil.multithreading.ex6;

public class MultiThreading extends Thread{

    public MultiThreading(String name) {
        super(name);
    }

    @Override
    public void run() {
       while (true){
           System.out.println("Hello World!");
       }
    }

    public static void main(String[] args) throws InterruptedException {

        //Daemon thread : Daemon thread is working in background(like garbage collector in java), jvm will not wait to complete execution of deamon thread.
        //                jvm will wait for user thread to complete its execution., not for daemon thread.

    /*    MultiThreading thread1 = new MultiThreading("Thread1");
        thread1.start();
        System.out.println("Main thread completed"); */     // here main thread's execution is completed but jvm will wait for user thread(i.e thread1) to complete.

    /*    MultiThreading thread1 = new MultiThreading("Thread1");
        MultiThreading thread2 = new MultiThreading("Thread2");
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        System.out.println("Main thread completed"); */    // here main thread's execution is completed but jvm will not wait for user thread(i.e thread1) to complete.

        MultiThreading thread1 = new MultiThreading("Thread1");
        thread1.setDaemon(true);
        thread1.start();
        System.out.println("Main thread completed");     // here main thread's execution is completed but jvm will not wait for daemon thread(i.e thread1) to complete.
    }
}
