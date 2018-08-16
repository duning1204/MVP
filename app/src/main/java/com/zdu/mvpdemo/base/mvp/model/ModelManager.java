package com.zdu.mvpdemo.base.mvp.model;


import android.support.v4.util.ArrayMap;

/**
 * @author : 杜宗宁
 * @date : 2018/7/28
 * model统一管理
 * 根据传递来的全类目，反射获取相对于的类
 * 新增重写方法，可以传递class
 */
public class ModelManager {

    private static ArrayMap<String, IBaseModel> mCache = new ArrayMap<>();

    /**
     * 传递类，反射获得该类对象
     *
     * @param token 类引用字符串
     */
    public static IBaseModel getModel(Class<? extends BaseModel> token) {
        return getModel(token.getName());
    }

    /**
     * 传递类引用字符串，反射获得该类对象
     *
     * @param token 类引用字符串
     */
    public static IBaseModel getModel(String token) {
        IBaseModel model = mCache.get(token);
        try {
            if (model == null) {
                model = (IBaseModel) Class.forName(token).newInstance();
                mCache.put(token, model);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return model;
    }

}
