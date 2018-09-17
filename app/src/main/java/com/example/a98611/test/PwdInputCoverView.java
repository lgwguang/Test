package com.example.a98611.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class PwdInputCoverView extends LinearLayout {
    /**
     * 密码对应的六个图片
     */
    public ImageView iv_transPwd1, iv_transPwd2, iv_transPwd3, iv_transPwd4, iv_transPwd5, iv_transPwd6;

    /**
     * 密码对应的六个图片，已经输入
     */
    public ImageView iv_transPwd1_done, iv_transPwd2_done, iv_transPwd3_done, iv_transPwd4_done, iv_transPwd5_done, iv_transPwd6_done;

    public PwdInputCoverView(Context context) {
        super(context);
    }

    public PwdInputCoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pwd_coverview, this);
        initView(view,context,attrs,0);
    }

    private void initView(View view, Context context, AttributeSet attrs, int defStyleAttr){
        iv_transPwd1 = (ImageView) view.findViewById(R.id.iv_transPwd1);
        iv_transPwd2 = (ImageView) view.findViewById(R.id.iv_transPwd2);
        iv_transPwd3 = (ImageView) view.findViewById(R.id.iv_transPwd3);
        iv_transPwd4 = (ImageView) view.findViewById(R.id.iv_transPwd4);
        iv_transPwd5 = (ImageView) view.findViewById(R.id.iv_transPwd5);
        iv_transPwd6 = (ImageView) view.findViewById(R.id.iv_transPwd6);
        iv_transPwd1_done = (ImageView) view.findViewById(R.id.iv_transPwd1_done);
        iv_transPwd2_done = (ImageView) view.findViewById(R.id.iv_transPwd2_done);
        iv_transPwd3_done = (ImageView) view.findViewById(R.id.iv_transPwd3_done);
        iv_transPwd4_done = (ImageView) view.findViewById(R.id.iv_transPwd4_done);
        iv_transPwd5_done = (ImageView) view.findViewById(R.id.iv_transPwd5_done);
        iv_transPwd6_done = (ImageView) view.findViewById(R.id.iv_transPwd6_done);
    }

    /**
     * 更改密码格子状态
     *
     * @param len 密码长度
     */
    public void setLength(int len)
    {
        switch (len)
        {
            case 0:
                iv_transPwd1_done.setVisibility(View.GONE);
                iv_transPwd2_done.setVisibility(View.GONE);
                iv_transPwd3_done.setVisibility(View.GONE);
                iv_transPwd4_done.setVisibility(View.GONE);
                iv_transPwd5_done.setVisibility(View.GONE);
                iv_transPwd6_done.setVisibility(View.GONE);
                iv_transPwd1.setVisibility(View.VISIBLE);
                iv_transPwd2.setVisibility(View.VISIBLE);
                iv_transPwd3.setVisibility(View.VISIBLE);
                iv_transPwd4.setVisibility(View.VISIBLE);
                iv_transPwd5.setVisibility(View.VISIBLE);
                iv_transPwd6.setVisibility(View.VISIBLE);
                break;
            case 1:
                iv_transPwd1.setVisibility(View.GONE);
                iv_transPwd1_done.setVisibility(View.VISIBLE);
                iv_transPwd2.setVisibility(View.VISIBLE);
                iv_transPwd2_done.setVisibility(View.GONE);
                break;
            case 2:
                iv_transPwd2.setVisibility(View.GONE);
                iv_transPwd2_done.setVisibility(View.VISIBLE);
                iv_transPwd3.setVisibility(View.VISIBLE);
                iv_transPwd3_done.setVisibility(View.GONE);
                break;
            case 3:
                iv_transPwd3.setVisibility(View.GONE);
                iv_transPwd3_done.setVisibility(View.VISIBLE);
                iv_transPwd4.setVisibility(View.VISIBLE);
                iv_transPwd4_done.setVisibility(View.GONE);
                break;
            case 4:
                iv_transPwd4.setVisibility(View.GONE);
                iv_transPwd4_done.setVisibility(View.VISIBLE);
                iv_transPwd5.setVisibility(View.VISIBLE);
                iv_transPwd5_done.setVisibility(View.GONE);
                break;
            case 5:
                iv_transPwd5.setVisibility(View.GONE);
                iv_transPwd5_done.setVisibility(View.VISIBLE);
                iv_transPwd6.setVisibility(View.VISIBLE);
                iv_transPwd6_done.setVisibility(View.GONE);
                break;
            case 6:
                iv_transPwd6.setVisibility(View.GONE);
                iv_transPwd6_done.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }

}
