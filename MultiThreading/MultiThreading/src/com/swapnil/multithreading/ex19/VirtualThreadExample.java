package com.swapnil.multithreading.ex19;

import java.util.concurrent.*;

public class VirtualThreadExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(1000); // simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();
        System.out.println("Virtual threads took: " + (end - start) + " ms");
    }
}
