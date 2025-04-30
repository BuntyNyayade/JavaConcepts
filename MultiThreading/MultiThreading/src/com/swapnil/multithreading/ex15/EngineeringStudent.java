package com.swapnil.multithreading.ex15;

public class EngineeringStudent implements Student{
    @Override
    public String getInfo(String name) {
        return name + " is engineering student";
    }
}
