package com.swapnil.multithreading.ex10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

//        It’s like lock.lock(), but with a twist:
//        ✅ It allows the thread to respond to interruption while waiting to acquire the lock.
//        ❌ lock.lock() will just block forever, even if the thread is interrupted.


        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Thread 1: Acquired lock, working...");
                Thread.sleep(5000); // hold the lock for 5 seconds
            } catch (InterruptedException e) {
                System.out.println("Thread 1 was interrupted!");
            } finally {
                lock.unlock();
                System.out.println("Thread 1: Lock released.");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Thread 2: Trying to acquire lock interruptibly...");
                lock.lockInterruptibly(); // This will wait and be interruptible, try lock.lock() here and check output
                try {
                    System.out.println("Thread 2: Acquired lock.");
                } finally {
                    lock.unlock();
                    System.out.println("Thread 2: Lock released.");
                }
            } catch (InterruptedException e) {
                System.out.println("Thread 2: Interrupted while waiting for lock.");
            }
        });

        thread1.start();

        try {
            Thread.sleep(100); // Ensure thread1 gets the lock first
        } catch (InterruptedException ignored) {}

        thread2.start();

        // Interrupt thread2 while it's waiting for the lock
        new Thread(() -> {
            try {
                Thread.sleep(1000); // wait a bit then interrupt
                System.out.println("Main: Interrupting thread 2...");
                thread2.interrupt();
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
