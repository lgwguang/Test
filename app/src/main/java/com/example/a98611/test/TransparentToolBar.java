package com.example.a98611.test;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 */
public class TransparentToolBar extends RelativeLayout {

    private static final String TAG = TransparentToolBar.class.getSimpleName();
    private static final String TRANS_COLOR = "#15000000";//默认透明颜色

    private float mOffset = 100;//ToolBar从透明到有颜色的偏移量
    private int mBackGroundColor ;//ToolBar背景色
    private OnScrollStateListener mOnScrollStateListener;

    public TransparentToolBar(Context context) {
        super(context);
        init();
    }

    public TransparentToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TransparentToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /** 初始化ToolBar为透明色 */
    private void init() {
        setBackgroundColor(Color.parseColor(TRANS_COLOR));
    }

    /** 设置ToolBar最大偏移量 */
    public void setOffset(float offset) {
        mOffset = offset;
    }

    /** 设置ToolBar背景色 */
    public void setBgColor(int color) {
        mBackGroundColor = color;
    }

    /** 改变ToolBar颜色透明度 */
    public void setChangeTop(float top) {
        //如果没有设置最大偏移量与背景色。则不会执行
        /*if (mOffset <= 0 && mBackGroundColor == 0) {
            return;
        }*/
        if (mOffset == 0 && mBackGroundColor == 0) {
            return;
        }
        //计算出透明度变化百分比 TRANS_RATE~1 设计需要一个初始透明度 如果要为全透明时TRANS_RATE为0
        float fraction = (top)/ mOffset;

        //百分比最小等于0
       /* if (fraction < 0) {
            fraction = 0;
        }*/

        //百分比最大等于1
        if (fraction > 1) {
            fraction = 1;
        }
        //百分比最小等于-1
        if (fraction < -1) {
            fraction = -1;
        }
        int newColor = 0;
        //计算出最新的背景色颜色值，设置到toolBar背景色中
        if(fraction==0) {
            setBackgroundColor(Color.parseColor(TRANS_COLOR));
        }else{
            newColor = changeAlpha(mBackGroundColor, fraction);
            if(fraction==1||fraction==-1){
                setBackgroundColor(newColor);
            }else{
                setBackgroundColor(newColor);
            }
        }
        if(mOnScrollStateListener != null) {
            mOnScrollStateListener.updateFraction(fraction);
        }

        Log.i(TAG, "mOffset:" + mOffset + " ,top:" + top + ", newColor:" + newColor + " ,fraction:" + fraction);
    }

    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * Math.abs(fraction));
        return Color.argb(alpha, red, green, blue);
    }

    public interface OnScrollStateListener {
        void updateFraction(float fraction);
    }

    public void setOnScrollStateListener(OnScrollStateListener listener) {
        mOnScrollStateListener = listener;
    }

}