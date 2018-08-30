package com.zdu.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.zdu.mvpdemo.base.mvp.presenter.BasePresenter;
import com.zdu.mvpdemo.base.mvp.presenter.PresenterLoader;
import com.zdu.mvpdemo.base.mvp.view.IBaseView;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * 使用loader解决屏幕状态改变时，拉取同一个Presenter的实例
 */
public class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements LoaderManager.LoaderCallbacks<P>, IBaseView {

    private final int LOADER_ID = TAG.hashCode();
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(LOADER_ID, savedInstanceState, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        mPresenter = null;
    }

    @NonNull
    @Override
    public Loader<P> onCreateLoader(int id, @Nullable Bundle args) {
        return new PresenterLoader<>(this, getClass());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<P> loader, P data) {
        if (mPresenter != null) {
            onViewVisibility();
        }
        if (mPresenter == null && data != null) {
            mPresenter = data;
            mPresenter.onAttach(this);
            onPresenterCreate();
        }
    }

    /**
     * view 可见状态（首次进入Act的时候不会调用）
     */
    private void onViewVisibility() {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<P> loader) {
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        mPresenter = null;
    }

    /**
     * 回调此方法，presenter创建完毕
     * 只调用一次
     */
    protected void onPresenterCreate() {

    }

}
