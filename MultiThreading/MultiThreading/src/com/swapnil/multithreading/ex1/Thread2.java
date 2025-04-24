package com.swapnil.multithreading.ex1;

//By extending Runnable interface
public class Thread2 implements Runnable{
    @Override
    public void run() {
        for (; ;) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
