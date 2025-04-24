package com.swapnil.multithreading.ex7;

public class Counter {

    private int count = 0;

    //Here shared resources are accessed and modified so this part is called critical section.
    public synchronized void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }
}
