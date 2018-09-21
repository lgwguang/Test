package com.example.a98611.test.mvp;

import android.os.Bundle;

import com.example.a98611.test.R;

public class MvpMainActivity extends BaseMvpActivity<MainContract.IMainPresenter> implements MainContract.IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.requestTestContent();
    }

    @Override
    protected MainContract.IMainPresenter createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    public void setTestContent() {

    }
}
