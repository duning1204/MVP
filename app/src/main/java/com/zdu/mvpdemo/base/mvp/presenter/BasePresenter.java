package com.zdu.mvpdemo.base.mvp.presenter;

import com.zdu.mvpdemo.base.mvp.view.IBaseView;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * Presenter基类，需要添加IBaseView子类的约束
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public V getView() {
        checkViewAttached();
        return mView;
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new NullPointerException("View 已为空");
        }
    }

    /**
     * 取消，置空数据，防止内存泄露
     */
    @Override
    public void onDetach() {
        mView = null;
    }

}
