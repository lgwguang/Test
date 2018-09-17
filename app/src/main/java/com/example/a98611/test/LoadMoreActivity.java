package com.example.a98611.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreActivity extends Activity {

    private ListView lv;
    private List list = new ArrayList();
    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadmore);
        lv = (ListView) findViewById(R.id.lv);
        setData();
    }



    private void setData() {
        for (int i = 0; i < 10; i++) {
            list.add("这是测试数据"+i);
        }

        adapter = new MyAdapter();
        lv.setAdapter(adapter);
        lv.setOnScrollListener(onScrollListener);
    }
    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            System.out.print(String.valueOf(lv.getLastVisiblePosition())+":"+String.valueOf(adapter.getCount()-1));
            if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){

                if(lv.getLastVisiblePosition() == adapter.getCount()-1 ){

                    for (int i = 10; i < 20; i++) {
                        list.add("这是测试数据"+i);
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(LoadMoreActivity.this);
            tv.setText(list.get(position).toString());
            tv.setTextSize(25);
            tv.setPadding(0,35,0,35);
            return tv;
        }
    }
}
