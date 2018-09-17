package com.example.a98611.test.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.a98611.test.R;


public class MyToggleBtn extends View implements View.OnClickListener {

    private Bitmap slideBtn;
    private Bitmap slideBg;
    private int width;
    private int height;
    private Paint paint;
    private float slideLeft;
    private boolean isoOpen;
    private int downX,lastX;
    private int maxDistance;
    private boolean isdrop;


    public MyToggleBtn(Context context) {
        super(context);
        initView();
    }

    public MyToggleBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        slideBtn = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        slideBg = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        maxDistance = slideBg.getWidth() - slideBtn.getWidth();
        paint = new Paint();
        paint.setAntiAlias(true);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(slideBg,0,0,paint);

        canvas.drawBitmap(slideBtn,slideLeft,0,paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = slideBg.getWidth();
        height = slideBg.getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width,height);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = lastX = (int) event.getX();
                isdrop = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceX = (int) (event.getX()-lastX);
                slideLeft+=distanceX;
                flushView();
                lastX = (int) event.getX();
                if(Math.abs(event.getX()-downX)>10){
                    isdrop = true;
                }
            break;
            case MotionEvent.ACTION_UP:
                if(isdrop){
                    if(slideLeft>maxDistance/2){
                        isoOpen = true;
                    }else{
                        isoOpen = false;
                    }
                    flushState();
                }
                break;
        }
        return true;
    }

    private void flushView() {
        if(slideLeft<0){
            slideLeft=0;
        }else if(slideLeft>maxDistance){
            slideLeft = maxDistance;
        }
        invalidate();
    }

    @Override
    public void onClick(View v) {
        if(isdrop){
            return;
        }
        isoOpen = !isoOpen;
        flushState();
    }

    private void flushState() {
        if(isoOpen){
            slideLeft = maxDistance;
        }else{
            slideLeft = 0;
        }
        flushView();
    }
}
