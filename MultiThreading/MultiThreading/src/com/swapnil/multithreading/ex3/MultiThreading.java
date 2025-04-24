package com.swapnil.multithreading.ex3;

public class MultiThreading extends Thread{

    public MultiThreading() {
    }

    public MultiThreading(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String a = "";
            for (int j = 0; j < 10000; j++) {
                a += "a";
            }
            System.out.println("Current Thread: " + Thread.currentThread().getName() + " Priority: " + Thread.currentThread().getPriority() + " Count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //Note: Its not strict rule that thread with high priority will execute first,
        // will only give hint to jvm or os scheduler

        MultiThreading min = new MultiThreading("MIN PRIORITY");
        MultiThreading norm = new MultiThreading("NORM PRIORITY");
        MultiThreading max = new MultiThreading("MAX PRIORITY");
        min.setPriority(Thread.MIN_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);
        min.start();
        norm.start();
        max.start();



    }
}
