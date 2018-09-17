package com.example.a98611.test.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a98611.test.bean.Ponit;

import java.util.ArrayList;
import java.util.List;


public class MyView extends View {

    private Paint paint,paintCircle,paintext;

    private List<Ponit> ponit = new ArrayList<>();

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);


        paintCircle = new Paint();
        paintCircle.setColor(Color.WHITE);
        paintCircle.setStrokeWidth(3);
        paintCircle.setAntiAlias(true);
        paintCircle.setStyle(Paint.Style.STROKE);



        paintext = new Paint();
        paintext.setColor(Color.RED);
        paintext.setAntiAlias(true);
        paintext.setTextSize(20);

        ponit.add(new Ponit(-70,-30,100,-300));
        ponit.add(new Ponit(100,-300,250,-50));
        ponit.add(new Ponit(250,-50,350,-100));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec/2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(150,500);
        canvas.drawColor(Color.GRAY);
        int distanceY = 0;
        for (int i = 0; i < 10; i++) {
            canvas.drawLine(-70,distanceY,500,distanceY,paintCircle);
            canvas.drawText(String.valueOf(Math.abs(distanceY)),-120,distanceY+5,paintext);

            distanceY-=50;
        }
        canvas.drawLine(-70,0,-70,-500,paintCircle);
        int distanceX = -70;
        int disX = 0;
        for (int i = 0; i < 10; i++) {
//            canvas.drawLine(distanceX,0,distanceX,-500,paintCircle);
            canvas.drawCircle(distanceX,0,6,paintCircle);
            canvas.drawText(String.valueOf(Math.abs(disX)),distanceX-5,30,paintext);
            distanceX+=50;
            disX+=50;
        }

        for (int i = 0; i < ponit.size(); i++) {
            Ponit ponit = this.ponit.get(i);

            canvas.drawLine(ponit.x1,ponit.y1,ponit.x2,ponit.y2,paint);
            canvas.drawCircle(ponit.x1,ponit.y1,5,paintCircle);
            canvas.drawText(String.valueOf(Math.abs(ponit.y1)),ponit.x1-15,ponit.y1-15,paintext);
            if(i==(this.ponit.size()-1)){
                canvas.drawCircle(ponit.x2,ponit.y2,5,paintCircle);
                canvas.drawText(String.valueOf(Math.abs(ponit.y2)),ponit.x2-15,ponit.y1-15,paintext);
            }
        }
    }
}
