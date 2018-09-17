package com.example.a98611.test.cacheutils;


import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存
 */
public class MemoryCacheUtils {

    private LruCache<String, Bitmap> memoryCacheUtils;

    public MemoryCacheUtils() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 8;
        memoryCacheUtils = new LruCache<String, Bitmap>(maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    /**
     * 写内存
     * @param str
     * @param bitmap
     */
    public void setBitmapToMemory(String str, Bitmap bitmap) {
        memoryCacheUtils.put(str, bitmap);
    }

    /**
     * 从内存读
     *
     * @param str
     * @return
     */
    public Bitmap getBitmaoToMemory(String str) {
        return memoryCacheUtils.get(str);
    }
}
