package com.swapnil.multithreading.ex1;

//By extending Thread class
public class ThreadExample extends Thread{

    @Override
    public void run() {

        for (; ;) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
