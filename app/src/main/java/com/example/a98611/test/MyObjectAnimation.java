package com.example.a98611.test;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class MyObjectAnimation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);
        TextView tv = (TextView) findViewById(R.id.tv);
//        tv.setTranslationX(translationX);
//        tv.setAlpha(alpha);
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"alpha",0,0.2f,0.5f,1);
        animator.setDuration(3000);
        animator.setRepeatCount(2);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
