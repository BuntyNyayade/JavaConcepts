package com.swapnil.multithreading.ex19;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(5000);
                System.out.println("Worker");
            }catch (Exception e){

            }
            return "Ok";
        });                                      //This is daemon thread, to wait use get() method

        try {
            String s = completableFuture.getNow("No result");   //This will give result if execution completed at this moment otherwise given statement
            System.out.println(s);
            s = completableFuture.get();    //this will wait for thread to execute completely,
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Main");



        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(5000);
                System.out.println("Worker1");
            }catch (Exception e){

            }
            return "Ok";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(5000);
                System.out.println("Worker2");
            }catch (Exception e){

            }
            return "Ok";
        });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFuture1, completableFuture2);   //This will wait for all threads to complete execution
//        voidCompletableFuture.get();
//        voidCompletableFuture.join();

        CompletableFuture<List<String>> voidCompletableFuture1 = CompletableFuture.allOf(completableFuture1, completableFuture2).thenApply(v -> {
            try {
                return Arrays.asList(completableFuture1.get(), completableFuture2.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        List<String> strings = voidCompletableFuture1.get();
        System.out.println(strings);

        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(5000);
                System.out.println("Worker2");
            }catch (Exception e){

            }
            return "Ok";
        }).orTimeout(1, TimeUnit.SECONDS).exceptionally(s -> "Timeout Occcured");

        System.out.println(  completableFuture3.get());

    }


}
