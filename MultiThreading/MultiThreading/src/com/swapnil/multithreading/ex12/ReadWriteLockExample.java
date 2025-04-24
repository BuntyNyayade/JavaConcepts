package com.swapnil.multithreading.ex12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private int count = 0;

    //ReadWriteLock allows multiple threads to read resources concurrently as long as no thread write into it.
    //It insures exclusive access for write operations
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try{

            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }finally {
            writeLock.unlock();
        }
    }

    public int getCount(){
        readLock.lock();             //Multiple threads can acquire this write lock, when no other thread acquired read lock
        try {

            return count;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {

        ReadWriteLockExample readWriteLockExample = new ReadWriteLockExample();

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + readWriteLockExample.getCount());
                }
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " incremented");
                    readWriteLockExample.increment();

                }
            }
        };

        Thread writeThread = new Thread(task2, "writeThread");
        Thread readThread1 = new Thread(task1, "readThread1");
        Thread readThread2 = new Thread(task1, "readThread2");

        writeThread.start();
        readThread1.start();
        readThread2.start();
    }
}
