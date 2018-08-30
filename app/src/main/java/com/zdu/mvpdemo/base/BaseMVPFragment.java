package com.zdu.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import com.zdu.mvpdemo.base.mvp.presenter.BasePresenter;
import com.zdu.mvpdemo.base.mvp.presenter.PresenterLoader;
import com.zdu.mvpdemo.base.mvp.view.IBaseView;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * 使用loader解决屏幕状态改变时，拉取同一个Presenter的实例
 */
public class BaseMVPFragment<P extends BasePresenter> extends BaseFragment implements LoaderManager.LoaderCallbacks<P>, IBaseView {

    protected final int LOADER_ID = TAG.hashCode();
    protected P mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, savedInstanceState, this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @NonNull
    @Override
    public Loader<P> onCreateLoader(int id, @Nullable Bundle args) {
        return new PresenterLoader<>(getContext(), getClass());
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

    /**
     * 首次进入页面会调用此方法
     */
    public void onPresenterCreate() {
    }

    @Override
    public void onLoaderReset(@NonNull Loader<P> loader) {
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        mPresenter = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        mPresenter = null;
    }
}
