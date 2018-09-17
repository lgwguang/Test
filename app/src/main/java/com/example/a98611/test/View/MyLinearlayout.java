package com.example.a98611.test.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * author: lgw
 * date: on 2017/5/10.
 */

public class MyLinearlayout extends LinearLayout {
    public MyLinearlayout(Context context) {
        super(context);
    }

    public MyLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int width = getWidth()/getChildCount();
        float eventX = event.getX();
        if(eventX < width){
            event.setLocation(width/2,event.getY());
            getChildAt(0).dispatchTouchEvent(event);
            return true;
        }else if(eventX>width && eventX<2*width){
//            event.setLocation(width/2,event.getY());
//            getChildAt(1).dispatchTouchEvent(event);
            int height = getHeight();
            if(event.getY() < height/2){
                for (int i = 0; i < getChildCount(); i++) {
                    View view = getChildAt(i);
                    view.dispatchTouchEvent(event);
                }
            }else if(event.getY() > height/2){
                event.setLocation(width/2,event.getY());
                getChildAt(1).dispatchTouchEvent(event);
            }


            return true;
        }else if(eventX > 2*width){
            event.setLocation(width,event.getY());
            getChildAt(2).dispatchTouchEvent(event);
            return true;
        }
        return true;
    }
}
