package com.swapnil.multithreading.ex7;

public class MultiThreading extends Thread{

    private Counter counter;

    public MultiThreading(Counter countr) {
        this.counter = countr;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //Here we shared common object(i.e counter) to both threads, so when they are running parallely its possible counter.increment()
        // executed at same time so you will not get counter.getCount() = 2000 in that case
        //ex: suppose counter = 101 both thread read same value and incremented to 102
        //Use synchronized keyword on method or block.(check in increment() method)

        Counter counter = new Counter();
        MultiThreading thread1 = new MultiThreading(counter);
        MultiThreading thread2 = new MultiThreading(counter);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(counter.getCount());
    }
}
