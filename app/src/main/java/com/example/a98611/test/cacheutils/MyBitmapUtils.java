package com.example.a98611.test.cacheutils;


import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.a98611.test.R;

public class MyBitmapUtils {

    NetCacheUtils mNetCacheUtils;
    MemoryCacheUtils mMemoryCacheUtils;
    LocalCacheUtils mLocalCacheUtils;

    public MyBitmapUtils(){
        mMemoryCacheUtils = new MemoryCacheUtils();
        mLocalCacheUtils = new LocalCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mMemoryCacheUtils,mLocalCacheUtils);
    }
    public void display(ImageView ivPic,String url){
        ivPic.setImageResource(R.drawable.rock_bg);
        Bitmap bitmap = null;
        //读取内存
        bitmap = mMemoryCacheUtils.getBitmaoToMemory(url);
        if(bitmap!=null){
            ivPic.setImageBitmap(bitmap);
            return;
        }
        //读取本地
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if(bitmap!=null){
            ivPic.setImageBitmap(bitmap);
            mMemoryCacheUtils.setBitmapToMemory(url,bitmap);
            return;
        }
        //网络获取
        mNetCacheUtils.getBitmapfromNet(ivPic,url);
    }
}
