package com.zdu.mvpdemo;

import android.os.Handler;

import com.zdu.mvpdemo.base.mvp.model.APIParamKey;
import com.zdu.mvpdemo.base.mvp.model.BaseModel;
import com.zdu.mvpdemo.base.mvp.model.CallBack;
import com.zdu.mvpdemo.base.mvp.model.HttpParam;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 */
public class MainModel extends BaseModel<MainBean> {

    @Override
    public void execute(final CallBack<MainBean> callBack) {
        super.execute(callBack);
        HttpParam param = getParam();
        String phone = param.get(APIParamKey.PHONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 4000);
    }

}
