package com.swapnil.multithreading.ex11;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Here, with fair lock, suppose thread1 acquired lock and thread2, thread3 are waiting then they will get chance who requested first
//Here its also not depend on start() sequence, suppose thread3 requested first then it will get chance to execute first.
//        thread1.start();
//        thread2.start();
//        thread3.start();

//Ex: supoose thread1 is executing, thread2 comes and waiting for lock to acquire, in mean time thread3, thread4 are also come requested to acquire lock
//so if multiple thread requested to acquire lock then they get chance according to their request sequence, not necessarily who invoked start() first.
//in such case every thread get chance fairly

//OUTPUT:
//Thread1 acquired the lock
//Thread1released the lock
//Thread3 acquired the lock
//Thread3released the lock
//Thread2 acquired the lock
//Thread2released the lock


public class FairLockExample {

    private final Lock unfairLock = new ReentrantLock(true);

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
        FairLockExample unfairLockExample = new FairLockExample();

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






