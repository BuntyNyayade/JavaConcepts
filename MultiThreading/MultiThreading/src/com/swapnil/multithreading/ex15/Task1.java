package com.swapnil.multithreading.ex15;

public class Task1 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
