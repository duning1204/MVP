package com.zdu.mvpdemo.base.mvp.model;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * 网络请求回调
 */
public abstract class CallBack<T> {
    /**
     * 数据请求成功
     *
     * @param data 请求到的数据
     */
    public abstract void onSuccess(T data);

    /**
     * 数据请求失败
     */
    public void onFailure(String msg) {

    }
}
