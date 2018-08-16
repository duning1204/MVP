package com.zdu.mvpdemo.base.mvp.presenter;

import com.zdu.mvpdemo.base.mvp.view.IBaseView;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 */
public interface IBasePresenter<V extends IBaseView> {

    void onAttach(V view);

    V getView();

    void onPause();

    void onResume();

    boolean isViewAttached();

    void checkViewAttached();

    void onDetach();

}
