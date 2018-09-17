package com.example.a98611.test.cacheutils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 本地缓存
 */
public class LocalCacheUtils {

    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cache";

    /**
     * 从本地sdcard读图片
     *
     * @param url
     * @return
     */
    public Bitmap getBitmapFromLocal(String url) {
        try {
            File file = new File(CACHE_PATH, url);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                return bitmap;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向sdcasrd写图片
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToLocal(String url, Bitmap bitmap) {
        try {
            File file = new File(CACHE_PATH, url);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
