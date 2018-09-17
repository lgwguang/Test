package com.example.a98611.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;


public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        return new MyBind();
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate中的方法");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("onStart中的方法");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy中的方法");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind中的方法");
        return super.onUnbind(intent);
    }

    /*class MyBind extends Binder implements IService{


        @Override
        public void invokeServiceMethod() {
            sayServices();
        }

    }*/
    private final IService.Stub mBinder = new IService.Stub(){


        @Override
        public void invokeServiceMethod() throws RemoteException {
            sayServices();
        }
    };

    public void sayServices(){
        System.out.println("服务中的方法调用了");
    }
}
