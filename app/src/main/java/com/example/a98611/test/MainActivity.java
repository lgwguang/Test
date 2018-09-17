package com.example.a98611.test;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a98611.test.UI.WaterListview;
import com.example.a98611.test.Utils.Utils;
import com.example.a98611.test.domain.Person;
import com.example.a98611.test.impl.PersonDaoImpl;
import com.example.a98611.test.service.IService;
import com.example.a98611.test.service.MyService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private TextView tv_dispaly, tv_density;
    private EditText coverView,et_input;
    private Intent intent1;
    private MyServiceConnection connection;
    private IService iService;
    private PersonDaoImpl daoImpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        float density = Utils.get(this);
        tv = (TextView) findViewById(R.id.tv);
        tv_dispaly = (TextView) findViewById(R.id.tv_dispaly);
        tv_density = (TextView) findViewById(R.id.tv_density);
        et_input = (EditText) findViewById(R.id.et_input);
        tv_density.setText("dp = px/设备密度\n" + "该设备密度是：" + density);

        tv_density.post(new Runnable() {
            @Override
            public void run() {

            }
        });

        showSize();

    }

    private void showSize() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();

        long maxMemory = Runtime.getRuntime().maxMemory();
        long memory = Runtime.getRuntime().freeMemory();
        tv_dispaly.setText(width + ":" + height + "\n" + maxMemory / 1024 / 1024 + ":" + memory / 1024 / 1024);
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
                coverView = CoverView;
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

    public void doClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button:

                /*Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:12345678"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);*/

                intent = new Intent();
                intent.setClass(this,MyCustom.class);
                intent.putExtra("data","2017");
                Bundle bundle = new Bundle();
                bundle.putString("bundle","成功！");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.button1:
                intent = new Intent();
                intent.setClass(this,LockActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent();
                intent.setClass(this,WaterListview.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent();
                intent.setClass(this,MyObjectAnimation.class);
                startActivity(intent);
                break;
            case R.id.button4:
                DownLoadManager dm = new DownLoadManager();
                dm.addObserver(new DetailView());
                dm.notifyObservers();
                break;
            case R.id.button5:
                intent1 = new Intent();
                intent1.setClass(this, MyService.class);
                startService(intent1);
                break;
            case R.id.button6:
                intent1.setClass(this, MyService.class);
                stopService(intent1);
                break;
            case R.id.button7:
                connection = new MyServiceConnection();
                bindService(intent1,connection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.button8:
                if(connection==null)
                    return;
                unbindService(connection);
                connection = null;
                break;
            case R.id.button9:
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
            case R.id.button10:
                /*intent1 = new Intent();
                intent1.setClass(this, RockService.class);
                startService(intent1);
                finish();*/
                intent1 = new Intent();
                intent1.setClass(this, LoadMoreActivity.class);
                startActivity(intent1);
                break;
            case R.id.button11:
                daoImpl = new PersonDaoImpl(this);
                Toast.makeText(this,"创建",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button12:
                daoImpl = new PersonDaoImpl(this);
                daoImpl.sava(new Person("zhangsan","15088888888"));
                Toast.makeText(this,"sava",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button13:
                daoImpl = new PersonDaoImpl(this);
                daoImpl.del("zhangsan");
                Toast.makeText(this,"del",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button14:
                daoImpl = new PersonDaoImpl(this);
                daoImpl.updta(new Person("zhangsan","18088888888"));
                Toast.makeText(this,"updta",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button15:
                daoImpl = new PersonDaoImpl(this);
                String phone = daoImpl.query("zhangsan");
                Toast.makeText(this,phone,Toast.LENGTH_SHORT).show();
                break;
            case R.id.button16:
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
            case R.id.button17:
                String location = null;
                String text = et_input.getText().toString();
                SQLiteDatabase database = SQLiteDatabase.openDatabase("data/data/com.example.a98611.test/files/address.db",null,SQLiteDatabase.OPEN_READONLY);
                Cursor cursor = database.rawQuery("select location from data2 where id =(select outkey from data1 where id = ?)", new String[]{text});
                while (cursor.moveToNext()){
                    location = cursor.getString(cursor.getColumnIndex("location"));
                }
                cursor.close();
                Toast.makeText(this,location,Toast.LENGTH_SHORT).show();
                break;
        }

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
