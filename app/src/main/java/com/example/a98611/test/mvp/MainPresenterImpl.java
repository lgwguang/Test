package com.example.a98611.test.mvp;

import android.os.Bundle;

public class MainPresenterImpl extends BaseMvpPresenter<MainContract.IMainView> implements MainContract.IMainPresenter {
    @Override
    public void requestTestContent() {
        //先进行非空判断
        if (isViewAttached()) {
            getView().setTestContent();
        }
    }
    @Override
    public void onMvpAttachView(MainContract.IMainView view, Bundle savedInstanceState) {
        super.onMvpAttachView(view, savedInstanceState);
    }

    /**
     *重写P层需要的生命周期，进行相关逻辑操作
     */
    @Override
    public void onMvpResume() {
        super.onMvpResume();
    }
}
