package com.example.a98611.test;

import android.content.Context;
/**
 *作者
 *时间
 *邮箱
 *名称
 */
public class Http {
    TransactionCallback successCallback;
    Context context;

    public Http(){
        function();
    }

    public Http(Context context,TransactionCallback successCallback){
        this.context = context;
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
