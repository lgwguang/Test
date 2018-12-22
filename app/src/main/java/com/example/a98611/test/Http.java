package com.example.a98611.test;

import android.content.Context;
/**

* 类的描述

* @author Administrator

* @Time 2018-11-14:49:01

* 描述

*/
public class Http {
    TransactionCallback successCallback;
    Context mContext;

    public Http(){
        function();
    }

    public Http(Context context,TransactionCallback successCallback){
        this.mContext = context;
        this.successCallback = successCallback;
        function();
    }

    private void function(){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(successCallback!=null)
                    successCallback.execute("hello、我是结果");
            }
        }.start();
    }
}
