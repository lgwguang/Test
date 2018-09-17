package com.example.a98611.test;


import java.util.Observable;

public class DownLoadManager extends Observable {

    @Override
    public synchronized boolean hasChanged() {
        return true;
    }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();

    }
}
