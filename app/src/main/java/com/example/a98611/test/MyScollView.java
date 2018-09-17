package com.example.a98611.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * author: lgw
 * date: on 2017/5/6.
 */

public class MyScollView extends ViewGroup{


    private GestureDetector gestureDetector;

    private Scroller myScroller;

    public MyScollView(Context context) {
        super(context);
        initView();
    }

    public MyScollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyScollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        myScroller = new Scroller(getContext());
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                scrollBy((int)v,0);
                System.out.println(v);
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        for (int j = 0; j < getChildCount(); j++) {
            View view = getChildAt(j);
            view.layout(j*getWidth(),0,getWidth()*(j+1),getHeight());
        }
    }
    private int downX;
    private int currIndex;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();
                int temp = currIndex;
                if((downX-upX)>getWidth()/2){
                    temp++;
                }else if((upX-downX)>getWidth()/2){
                    temp--;
                }
                MoveToDesc(temp);
                break;
        }
        return true;
    }

    private void MoveToDesc(int temp) {
        if(temp < 0){
            temp = 0;
        }else if(temp > getChildCount()-1){
            temp= getChildCount()-1;
        }
        currIndex = temp;
        scrollTo(getWidth()*currIndex,0);
//        myScroller.startScroll(getScrollX(),0,getWidth()*currIndex-getScrollX(),0);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
