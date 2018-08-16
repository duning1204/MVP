package com.zdu.mvpdemo.base.mvp.model;

/**
 * @author : 杜宗宁
 * @date : 2018/8/11
 */
public interface IBaseModel<T> {
    IBaseModel<T> setParam(HttpParam param);

    HttpParam getParam();

    void execute(CallBack<T> callBack);

    void execute();
}
