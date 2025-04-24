package com.swapnil.multithreading.ex5;

public class MultiThreading extends Thread{

    public MultiThreading(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            Thread.yield();                       //This will provide hint to jvm that it can give chance to other threads or ignore current for now
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //Here you can compare result with commenting yield() method

        MultiThreading thread1 = new MultiThreading("Thread1");
        MultiThreading thread2 = new MultiThreading("Thread2");
        thread1.start();
        thread2.start();

    }
}
