package com.example.a98611.test.mvp;

public class MainContract {

    public interface IMainView extends MvpView {

        /**
         * 测试
         */
        void setTestContent();
    }

    public interface IMainPresenter extends MvpPresenter<IMainView> {

        /**
         * 测试
         */
        void requestTestContent();
    }
}
