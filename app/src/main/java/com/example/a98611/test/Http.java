package com.example.a98611.test;

import android.content.Context;
/**

* 类的描述

* @author Administrator

* @Time 2012-11-2014:49:01

*

*/

/**
  * private String id;

  * private String senderName;//发送人姓名

  * private String title;//不能超过120个中文字符

  * private String content;//邮件正文

  * private String attach;//附件，如果有的话

  * private String totalCount;//总发送人数

  * private String successCount;//成功发送的人数

  * private Integer isDelete;//0不删除 1删除

  * private Date createTime;//目前不支持定时 所以创建后即刻发送

  * privateSet<EmailList> EmailList;
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
