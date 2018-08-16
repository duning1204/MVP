package com.zdu.mvpdemo.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class BaseActivity extends AppCompatActivity {

    protected final String TAG = getClass().getName();

    /**
     * 当Activity的布局改动时，即setContentView()或者addContentView()方法执行完毕时就会调用该方法
     */
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initViews();
        initEvent();
        initData();
    }

    public void initViews() {

    }

    public void initData() {

    }

    public void initEvent() {

    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

}
