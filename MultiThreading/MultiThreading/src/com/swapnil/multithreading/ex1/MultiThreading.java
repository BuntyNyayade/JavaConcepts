package com.swapnil.multithreading.ex1;

public class MultiThreading {
    public static void main(String[] args) {

        ThreadExample threadExample = new ThreadExample();
        threadExample.start();

        RunnableExample runnableExample = new RunnableExample();
        Thread thread = new Thread(runnableExample);
        thread.start();


        for (; ;) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}