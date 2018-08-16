package com.zdu.mvpdemo.base.mvp.presenter;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * 注解解析器，创建Presenter
 */
public class PresenterLoader<P extends BasePresenter> extends Loader<P> {

    private CreatePresenter mCreatePresenter;
    private P mPresenter;

    public PresenterLoader(Context context, Class<?> tClass) {
        super(context);
        mCreatePresenter = tClass.getAnnotation(CreatePresenter.class);
    }

    private P getPresenter() {
        if (mCreatePresenter != null) {
            Class<P> pClass = (Class<P>) mCreatePresenter.value();
            try {
                return pClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
            }
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mCreatePresenter == null) {
            forceLoad();
        } else {
            deliverResult(mPresenter);
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mPresenter = getPresenter();
        deliverResult(mPresenter);
    }
}
