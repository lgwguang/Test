package com.example.a98611.test.cacheutils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络缓存
 */
public class NetCacheUtils {

    private MemoryCacheUtils mMemoryCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;

    public NetCacheUtils(MemoryCacheUtils memoryCacheUtils, LocalCacheUtils localCacheUtils) {
        this.mMemoryCacheUtils = memoryCacheUtils;
        this.mLocalCacheUtils = localCacheUtils;
    }

    public void getBitmapfromNet(ImageView ivPic, String url) {
        new BitmapTask().execute(ivPic, url);
    }

    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {
        private ImageView ivPic;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... params) {
            ivPic = (ImageView) params[0];
            url = (String) params[1];

            ivPic.setTag(url);

            return dowmloadBitmap(url);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result == null) {
                return;
            }
            String urlBind = (String) ivPic.getTag();
            if (url.equals(urlBind)) {
                ivPic.setImageBitmap(result);
                mMemoryCacheUtils.setBitmapToMemory(url, result);
                mLocalCacheUtils.setBitmapToLocal(url, result);
            }

        }
    }

    private Bitmap dowmloadBitmap(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inputStream = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            conn.disconnect();
        }
        return null;
    }
}
