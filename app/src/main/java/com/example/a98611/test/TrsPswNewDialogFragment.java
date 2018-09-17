package com.example.a98611.test;


import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class TrsPswNewDialogFragment extends DialogFragment {
    public static final String Trans_Password = "TRSPassword";
    private TextView tvTitle;
    private ImageView tvDel;
    private EditText seTransPwd;
    private PwdInputCoverView coverView;
    private TextView tvHint;
    private Button btnConfirm;

    private String title="请输入取款密码";
    private String hint="密码输入错误请重新输入";


    public interface Callback {
        void onResult(EditText se_transPwd,String str);
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_trans_psw,container);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvDel = (ImageView) view.findViewById(R.id.tvDel);
        seTransPwd = (EditText) view.findViewById(R.id.se_transPwd);
        coverView = (PwdInputCoverView) view.findViewById(R.id.coverView);
        tvHint = (TextView) view.findViewById(R.id.tvHint);
        btnConfirm = (Button) view.findViewById(R.id.btnConfirm);
        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback!=null){
//                    callback.onResult(seTransPwd.getValue(String.valueOf(Constant.TimeStamp + System.currentTimeMillis())));
                    callback.onResult(seTransPwd,seTransPwd.getText().toString());
                    dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getTimeStamp();
        initUI();
        changeWindowStyle();
    }
    private void changeWindowStyle(){
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        DisplayMetrics metric = new DisplayMetrics();
        getDialog().getWindow().getWindowManager().getDefaultDisplay().getMetrics(metric);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = (int) (metric.widthPixels * 0.8);
        /*p.y = p.y - Util.dip2px(getDialog().getContext().getApplicationContext(), 30);*/
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().getWindow().setAttributes(p);
        this.getDialog().onWindowAttributesChanged(p);
    }
    private void initUI() {
//        InputMethodManager imm = (InputMethodManager)seTransPwd.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(seTransPwd,InputMethodManager.SHOW_FORCED);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        tvTitle.setText(title);
        ToggleBtnConfirm(false);

       /* Util.initSAEditTextForTrs(seTransPwd, "password");
        seTransPwd.openSAKbd();*/
        seTransPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(tvHint.getVisibility()== View.VISIBLE){
//                    tvHint.setTextColor(getResources().getColor(R.color.gray));
                    tvHint.setVisibility(View.INVISIBLE);
                }
               if (seTransPwd.getText().length() == 6) {
                    ToggleBtnConfirm(true);
                }else{
                    ToggleBtnConfirm(false);
                }
                if (coverView != null) {
                    coverView.setLength(seTransPwd.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void ToggleBtnConfirm(boolean open){
       /* if(open){
            btnConfirm.setClickable(true);
            btnConfirm.setBackgroundResource(R.drawable.selector_loan_psw_btn);
        }else{
            btnConfirm.setClickable(false);
            btnConfirm.setBackgroundResource(R.drawable.shape_loan_btn_gray);
        }*/
    }
    private void getTimeStamp() {
        /*new HttpTask(getDialog().getContext(), HttpHelper.GetTimeStamp, new JSONObject(), NetUtils.POST, new TransactionCallback() {

            @Override
            public void execute(JSONObject jsonObject) {
                long serviceTime = Long.parseLong(JSONUtil.getString(jsonObject, "timeStamp"));// 原本代码,请还原
                long nowTime = System.currentTimeMillis();
                Constant.TimeStamp = serviceTime - nowTime;
            }

        }).setDialogMsg("").execute();*/
    }

//                intent = new Intent();
//                intent.putExtra(Trans_Password, seTransPwd.getValue(String.valueOf(Constant.TimeStamp + System.currentTimeMillis())));
//                getActivity().setResult(getActivity().RESULT_OK, intent);
//                dismiss();

    /**密码错误*/
    public void pswError(String str){
        coverView.setLength(0);
       /* seTransPwd.clear();*/
        tvHint.setVisibility(View.VISIBLE);
        if(str.contains("交易失败：实时交易发送核心记账异常！")){
            str = str.replace("交易失败：实时交易发送核心记账异常！","");
        }
        tvHint.setText(str);
        ToggleBtnConfirm(false);
    }
}