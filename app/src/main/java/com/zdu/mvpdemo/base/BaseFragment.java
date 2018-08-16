package com.zdu.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment {

    protected final String TAG = getClass().getName();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initEvent();
        initData();
    }

    public void initViews(View view) {

    }

    public void initData() {

    }

    public void initEvent() {

    }

}
