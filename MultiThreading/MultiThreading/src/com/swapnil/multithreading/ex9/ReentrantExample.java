package com.swapnil.multithreading.ex9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

    private final Lock lock = new ReentrantLock();

    public void outerMethod(){

        lock.lock();
        try {
            System.out.println("Outer method");
            innerMethod();
        }finally {
            lock.unlock();
     //     lock.unlock();                    //Here, we tried to unlock at 3rd but we locked 2 times only, it will throw exception
        }
    }

    public void innerMethod(){
        lock.lock();                          //Here, it will not throw error as its Reentrant lock, it will maintain count.[Exception in thread "main" java.lang.IllegalMonitorStateException]
        try {
            System.out.println("Inner method");
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();
        reentrantExample.outerMethod();
    }
}
