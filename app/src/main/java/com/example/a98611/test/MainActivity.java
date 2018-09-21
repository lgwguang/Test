package com.example.a98611.test;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a98611.test.UI.WaterListview;
import com.example.a98611.test.domain.Person;
import com.example.a98611.test.impl.PersonDaoImpl;
import com.example.a98611.test.service.IService;
import com.example.a98611.test.service.MyService;
import com.example.a98611.test.service.RockService;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {


    private EditText et_input;
    private Intent intent1;
    private MyServiceConnection connection;
    private IService iService;
    private PersonDaoImpl daoImpl;
    private RecyclerView recyclerview;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView)findViewById(R.id.recyclerview);

        list = new ArrayList<>();
        list.add("滑动动画");
        list.add("手势密码");
        list.add("安全密码");
        list.add("瀑布流");
        list.add("属性动画");
        list.add("观察者模式");
        list.add("开启服务");
        list.add("关闭服务");
        list.add("绑定服务");
        list.add("解绑服务");
        list.add("调用方法");
        list.add("加载更多");
        list.add("创建数据库");
        list.add("保存");
        list.add("删除");
        list.add("更新");
        list.add("查询");
        list.add("复制数据库");
        list.add("号码查询");
        list.add("悬浮窗火箭");
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.setAdapter(new DataAdapter(list));
    }

    class DataAdapter extends RecyclerView.Adapter<ViewHolder>{
        List<String> list;
        public DataAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = getLayoutInflater().inflate(R.layout.item_main, null);
            ViewHolder viewHolder = new ViewHolder(inflate);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tv.setText(list.get(position));
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            item = (LinearLayout) itemView.findViewById(R.id.item);
        }
    }

/*===================================================================================*/

    public void click(int position){
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(this,MyCustom.class);
                intent.putExtra("data","2017");
                Bundle bundle = new Bundle();
                bundle.putString("bundle","成功！");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent();
                intent.setClass(this,LockActivity.class);
                startActivity(intent);
                break;
            case 2:
                showPWDDialog();
                break;
            case 3:
                intent = new Intent();
                intent.setClass(this,WaterListview.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent();
                intent.setClass(this,MyObjectAnimation.class);
                startActivity(intent);
                break;
            case 5:
                DownLoadManager dm = new DownLoadManager();
                dm.addObserver(new DetailView());
                dm.notifyObservers();
                break;
            case 6:
                intent1 = new Intent();
                intent1.setClass(this, MyService.class);
                startService(intent1);
                break;
            case 7:
                intent1.setClass(this, MyService.class);
                stopService(intent1);
                break;
            case 8:
                connection = new MyServiceConnection();
                bindService(intent1,connection, Service.BIND_AUTO_CREATE);
                break;
            case 9:
                if(connection==null)
                    return;
                unbindService(connection);
                connection = null;
                break;
            case 10:
                if(iService!=null){
                    try {
                        iService.invokeServiceMethod();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(this, "请先绑定服务", Toast.LENGTH_SHORT).show();
                }
                break;
            case 11:
                intent1 = new Intent();
                intent1.setClass(this, LoadMoreActivity.class);
                startActivity(intent1);
                break;
            case 12:
                daoImpl = new PersonDaoImpl(this);
                Toast.makeText(this,"创建",Toast.LENGTH_SHORT).show();
                break;
            case 13:
                daoImpl = new PersonDaoImpl(this);
                daoImpl.sava(new Person("zhangsan","15088888888"));
                Toast.makeText(this,"sava",Toast.LENGTH_SHORT).show();
                break;
            case 14:
                daoImpl = new PersonDaoImpl(this);
                daoImpl.del("zhangsan");
                Toast.makeText(this,"del",Toast.LENGTH_SHORT).show();
                break;
            case 15:
                daoImpl = new PersonDaoImpl(this);
                daoImpl.updta(new Person("zhangsan","18088888888"));
                Toast.makeText(this,"updta",Toast.LENGTH_SHORT).show();
                break;
            case 16:
                daoImpl = new PersonDaoImpl(this);
                String phone = daoImpl.query("zhangsan");
                Toast.makeText(this,phone,Toast.LENGTH_SHORT).show();
                break;
            case 17:
                File file = new File(getFilesDir(),"address.db");
                if(file.exists()&&file.length()>0){
                    Log.d("TAG=======","已经存在了");
                    return;
                }
                try {
                    InputStream is = getAssets().open("address.db");
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = is.read(buffer))!=-1){
                        fos.write(buffer,0,len);
                    }
                    fos.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 18:
                String location = null;
                et_input = new EditText(this);
                et_input.setText("11111111");
                String text = "qqqqq";
                SQLiteDatabase database = SQLiteDatabase.openDatabase("data/data/com.example.a98611.test/files/address.db",null,SQLiteDatabase.OPEN_READONLY);
                Cursor cursor = database.rawQuery("select location from data2 where id =(select outkey from data1 where id = ?)", new String[]{text});
                while (cursor.moveToNext()){
                    location = cursor.getString(cursor.getColumnIndex("location"));
                }
                cursor.close();
                Toast.makeText(this,location,Toast.LENGTH_SHORT).show();
                break;
            case 19:
                intent1 = new Intent();
                intent1.setClass(this, RockService.class);
                startService(intent1);
                finish();
                break;
        }
    }

    public void click(View view) {
        showPWDDialog();
        new Http(this, new TransactionCallback() {
            @Override
            public void execute(final String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void showPWDDialog() {
        TrsPswNewDialogFragment dialog = new TrsPswNewDialogFragment();
        dialog.show(getFragmentManager(), "");
        dialog.setCallback(new TrsPswNewDialogFragment.Callback() {

            @Override
            public void onResult(EditText CoverView, String str) {

                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static class CountingFragment extends Fragment {
        int mNum;

        static CountingFragment newInstnce(int num) {
            CountingFragment f = new CountingFragment();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }
    }

    private void setDialogOnClickListener(int buttonId, final int dialogId) {
        Button b = (Button) findViewById(buttonId);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(dialogId);
            }
        });
    }

    private void loadBitmap(int id, ImageView ivContent) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), id, opts);
        int bmWidth = opts.outWidth;
        int bmHeigh = opts.outHeight;

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHigh = display.getHeight();

        int scale = 1;
        int dx = bmWidth / screenWidth;
        int dy = bmHeigh / screenHigh;

        if (dx > dy && dy > 0) {
            scale = dy;
        }
        if (dy > dx && dx > 0) {
            scale = dy;
        }
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = scale;
        Bitmap bm = BitmapFactory.decodeResource(getResources(), id, opts);
        ivContent.setImageBitmap(bm);
    }

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            iService = (IService) iBinder;
            iService = IService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iService = null;
        }
    }



}
