package com.swapnil.multithreading.ex8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private final Lock lock = new ReentrantLock();

    private int balance = 100;

    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() + "attempting to withdraw" + amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if (balance >= amount){
                    System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                    try {
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + "completed withdrawal.Remaining balance: " + balance);

                    } catch (Exception e) {
                        //It is good practice to re interrupt current thread, because if its get interrupted and we put logger only then all
                        //information of that thread is lost, which is not good for monitoring tool if any. if we re interrupt like below then we can
                        //catch current thread state[suggested by sonarlint plugin]
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();
                    }

                }else {
                    System.out.println(Thread.currentThread().getName() + "insufficient balance");
                }
            }else{
                System.out.println(Thread.currentThread().getName() + "could not acquire lock, will try later");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if(Thread.currentThread().isInterrupted()){
            System.out.println(Thread.currentThread().getName() + " is Interrupted");
        }

    }
}
