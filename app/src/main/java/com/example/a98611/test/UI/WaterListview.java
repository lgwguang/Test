package com.example.a98611.test.UI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.a98611.test.R;

/**
 * author: lgw
 * date: on 2017/5/10.
 */

public class WaterListview extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        ListView lv1 = (ListView) findViewById(R.id.lv1);
        ListView lv2 = (ListView) findViewById(R.id.lv2);
        ListView lv3 = (ListView) findViewById(R.id.lv3);
        lv1.setAdapter(new MyAdapter());
        lv2.setAdapter(new MyAdapter());
        lv3.setAdapter(new MyAdapter());
    }
    private int[] ids = new int[]{R.drawable.bankicon_ns3x,R.drawable.bankicon_ny3x,R.drawable.bankicon_pf3x};
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 3000;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView iv = new ImageView(WaterListview.this);
            int id = (int)(Math.random()*3);
            iv.setImageResource(ids[id]);
            return iv;
        }
    }
}
