package com.swapnil.multithreading.ex16;

public class FactorialWithoutExecutorV2 {

    public int factorial(int n) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (n == 0 || n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        FactorialWithoutExecutorV3 factorialWithoutExecutor = new FactorialWithoutExecutorV3();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            //Variable used in lambda expression should be final or effectively final(var which assigned only once).
            //Here if we used directly i in lambda, then it will give above error, lambda captures the value not variable itself
            //If i were allowed to change later, it would be unclear which value the lambda is using — the one at lambda creation or later.
            Thread thread = new Thread(()->{
                System.out.println(factorialWithoutExecutor.factorial(finalI));
            });
            thread.start();

        }

        long endTime = System.currentTimeMillis();

        System.out.println("Total time taken: " + (endTime - startTime)); //Here timing is incorrect as main thread executes before other threads are might be running
    }
}
