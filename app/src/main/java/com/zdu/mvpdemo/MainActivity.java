package com.zdu.mvpdemo;

import android.os.Bundle;
import android.util.Log;

import com.zdu.mvpdemo.base.BaseMVPActivity;
import com.zdu.mvpdemo.base.mvp.presenter.CreatePresenter;

@CreatePresenter(MainPresenter.class)
public class MainActivity extends BaseMVPActivity<MainPresenter> implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPresenterCreate() {
        mPresenter.show();
    }

    @Override
    public void show() {
        Log.d("MainActivity", "呵呵");
    }
}
