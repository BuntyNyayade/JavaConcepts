package com.swapnil.multithreading.ex13;

class Task3 implements Runnable {
    private Pen pen;
    private Paper paper;

    public Task3(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen and tries to lock paper
    }
}

class Task4 implements Runnable {
    private Pen pen;
    private Paper paper;

    public Task4(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        synchronized (pen){                  // Here, thread2 wait, to acquire lock on pen obj, which is acquired by thread1 now.
            paper.writeWithPaperAndPen(pen); // thread2 locks paper and tries to lock pen
        }
    }
}


public class DeadlockExampleSol {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task3(pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task4(pen, paper), "Thread-2");

        thread1.start();
        thread2.start();
    }
}