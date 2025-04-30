package com.swapnil.multithreading.ex19;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample2 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(5000);
                System.out.println("Worker");
            }catch (Exception e){

            }
            return "Ok";
        }, executorService);                      // you can assign predefined thread here

        System.out.println("Main");
    }
}
