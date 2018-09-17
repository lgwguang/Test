package com.example.a98611.test;


import java.util.Observable;
import java.util.Observer;

public class DetailView implements Observer {
    @Override
    public void update(Observable observable, Object o) {
        System.out.println("变化了");
    }
}
