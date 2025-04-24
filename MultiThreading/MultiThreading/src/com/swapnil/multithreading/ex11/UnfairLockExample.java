package com.swapnil.multithreading.ex11;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Here, with unfair lock, suppose thread1 acquired lock and thread2, thread3 are waiting then any of them will get chance to acquire lock
//not necessarily depends on this order:
//        thread1.start();
//        thread2.start();
//        thread3.start();

//Ex: supoose thread1 is executing, thread2 comes and waiting for lock to acquire, in mean time thread3, thread4 are also come requested to acquire lock
//so if multiple thread requested to acquire lock any one of them get chance randomly not necessarily who invoked start() first.
//in such case its possible that any thread will get chance at last even if its requested at same time is called starvation


public class UnfairLockExample {

    private final Lock unfairLock = new ReentrantLock();

    public void accessResource(){
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName() + "released the lock");
            unfairLock.unlock();

        }
    }

    public static void main(String[] args) {
        UnfairLockExample unfairLockExample = new UnfairLockExample();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                unfairLockExample.accessResource();
            }
        };

        Thread thread1  = new Thread(runnable, "Thread1");
        Thread thread2  = new Thread(runnable, "Thread2");
        Thread thread3  = new Thread(runnable, "Thread3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}






