package com.swapnil.multithreading.ex1;

public class MultiThreading {
    public static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        thread1.start();

        Thread2 thread2 = new Thread2();
        Thread thread_1 = new Thread(thread2);
        thread_1.start();


        for (; ;) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}