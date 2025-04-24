package com.swapnil.multithreading.ex8;

public class Main{

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                bankAccount.withdraw(50);
            }
        };

        Thread thread1 = new Thread(task, "Thread1");
        Thread thread2 = new Thread(task, "Thread2");
        thread1.start();
        thread2.start();
        System.out.println(bankAccount);
    }
}
