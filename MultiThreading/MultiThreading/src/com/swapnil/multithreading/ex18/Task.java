package com.swapnil.multithreading.ex18;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Hello World";
    }
}
