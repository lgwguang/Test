package com.example.a98611.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class SpliashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spliash);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        float densityDpi = dm.densityDpi;

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();

        long maxMemory = Runtime.getRuntime().maxMemory();
        long memory = Runtime.getRuntime().freeMemory();

        Toast.makeText(this,"=====",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"+++++",Toast.LENGTH_SHORT).show();


        TextView tv_info = (TextView) findViewById(R.id.tv_info);
        findViewById(R.id.btn_go).setOnClickListener(myListrner);
        String info = String.format("dp = px/设备密度\n该设备密度是：%f,\ndensityDpi是:%f.\nwidth:%d,height:%d,\nmaxMemory:%d,memory:%d",
                density,densityDpi,width,height,maxMemory / 1024 / 1024,memory / 1024 / 1024);
        tv_info.setText(info);
    }

    public View.OnClickListener myListrner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(mContext,MainActivity.class));
            finish();
        }
    };
}
