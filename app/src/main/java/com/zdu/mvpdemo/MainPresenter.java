package com.zdu.mvpdemo;

import com.zdu.mvpdemo.base.mvp.model.APIParamKey;
import com.zdu.mvpdemo.base.mvp.model.CallBack;
import com.zdu.mvpdemo.base.mvp.model.HttpParam;
import com.zdu.mvpdemo.base.mvp.model.ModelManager;
import com.zdu.mvpdemo.base.mvp.model.Token;
import com.zdu.mvpdemo.base.mvp.presenter.BasePresenter;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 */
public class MainPresenter extends BasePresenter<IMainView> {

    public void show() {
        HttpParam param = HttpParam.obtain();
        param.put(APIParamKey.PHONE, "13112345678");

        ModelManager.getModel(Token.MAIN_MODE)
                .setParam(param)
                .execute(new CallBack<MainBean>() {
                    @Override
                    public void onSuccess(MainBean data) {
                        mView.show();
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
    }
}