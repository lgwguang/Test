package com.example.a98611.test.Utils;


import android.content.Context;

public class Utils {
    /**
     * 获取设备密度
     * @param context
     * @return
     */
    public static float get(Context context){
        float density = context.getResources().getDisplayMetrics().density;
        return density;
    }
}
