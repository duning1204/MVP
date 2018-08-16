package com.zdu.mvpdemo.base.mvp.presenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * 注解创建Presenter
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {

    Class<? extends BasePresenter> value();
}
