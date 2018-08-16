package com.zdu.mvpdemo.base.mvp.model;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * Model基类
 */
public class BaseModel<T> implements IBaseModel<T> {

    private HttpParam mParam;

    /**
     * 传参
     *
     * @param param 参数集合
     */
    @Override
    public BaseModel<T> setParam(HttpParam param) {
        this.mParam = param;
        mParam.setUse(true);
        return this;
    }

    @Override
    public HttpParam getParam() {
        if (mParam == null) {
            throw new NullPointerException("入参为空");
        }
        return mParam;
    }

    /**
     * 执行异步操作
     *
     * @param callBack<T> 回调
     */
    @Override
    public void execute(CallBack<T> callBack) {
        recycleParam();
    }

    /**
     * 执行异步操作，无需回调
     */
    @Override
    public void execute() {
        recycleParam();
    }

    private void recycleParam() {
        if (mParam != null) {
            mParam.setUse(false);
            mParam.recycle();
        }
    }
}
