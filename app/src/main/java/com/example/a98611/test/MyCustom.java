package com.example.a98611.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;


public class MyCustom extends Activity {

    private int[] ids ={R.mipmap.a1,R.mipmap.a2,R.mipmap.a3};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        Bundle extras = intent.getExtras();
        String bundle = extras.getString("bundle");
        Toast.makeText(this, bundle, Toast.LENGTH_SHORT).show();

        MyScollView myScollView = (MyScollView) findViewById(R.id.sv);
        for (int i = 0; i < ids.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setBackgroundResource(ids[i]);
            myScollView.addView(iv);
        }


    }
}
