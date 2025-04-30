package com.swapnil.multithreading.ex19;

import java.util.concurrent.*;

public class NormalThreadExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(200); // limit because normal threads are heavy

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
        System.out.println("Normal threads took: " + (end - start) + " ms");
    }
}
