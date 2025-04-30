package com.swapnil.multithreading.ex15;

public class LambdaExpression{

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Thread thread1 = new Thread(task1, "Thread1");
        thread1.start();

        //Here, Runnable is functional Interface() which means it has single abstract method(method with no body).
        //Following is anonymous class(class with no name) as we implemented Runnable on fly

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        Thread thread2 = new Thread(task2, "Thread2");
        thread2.start();

        //Following is lambda expression(anonymous function) of above
        //So we can say that, lambda expression can be assigned to any functionalInterface.

        Runnable task3 = () -> System.out.println(Thread.currentThread().getName());
        Thread thread3 = new Thread(task3, "Thread3");
        thread3.start();

        Thread thread4 = new Thread(() -> System.out.println(Thread.currentThread().getName()), "Thread4");
        thread4.start();

        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("No :" + i);
            }
        }, "Thread5");
        thread5.start();

        Student engineeringStudent = new EngineeringStudent();
        System.out.println(engineeringStudent.getInfo("swapnil"));

        Student medicalStudent  = new Student() {
            @Override
            public String getInfo(String name) {
                return name +  " is medical student";
            }
        };

        System.out.println(medicalStudent.getInfo("ajit"));

        //Lambda expression with parameters

        Student commerceStudent  = (String name) -> {return name +  " is commerce student";};
        System.out.println(commerceStudent.getInfo("sonu"));

        //More cleaner, as we have single parameter we can remove (), also we have single statement so ultimately its return statement so removed

        Student baStudent  = name -> name +  " is BA student";
        System.out.println(baStudent.getInfo("monu"));


    }
}
