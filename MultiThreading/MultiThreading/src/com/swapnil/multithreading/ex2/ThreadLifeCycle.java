package com.swapnil.multithreading.ex2;

public class ThreadLifeCycle extends Thread{

    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);         //Sleep current thread i.e multiThreading thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadLifeCycle multiThreading = new ThreadLifeCycle();
        System.out.println(multiThreading.getState());
        multiThreading.start();
        System.out.println(multiThreading.getState());
        System.out.println(Thread.currentThread().getState());
        Thread.sleep(100);              //Sleep current thread i.e main thread , so multithreading thread get time to execute explicitly
        System.out.println(multiThreading.getState());
        multiThreading.join();                //Here main thread wait for multithreading thread to complete its execution then go to next step
        System.out.println(multiThreading.getState());

    }
}
