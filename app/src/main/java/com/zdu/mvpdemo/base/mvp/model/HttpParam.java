package com.zdu.mvpdemo.base.mvp.model;

import android.support.v4.util.ArrayMap;

/**
 * @author : 杜宗宁
 * @date : 2018/8/15
 * 网络请求入参类
 * 设置了缓冲池
 */
public class HttpParam extends ArrayMap<String, String> {
    /**
     * 锁对象
     */
    private static final Object sPoolSync = new Object();

    private static final int MAX_POOL_SIZE = 10;

    private static HttpParam sPool;

    private static int sPoolSize = 0;

    private boolean use;

    public void setUse(boolean use) {
        this.use = use;
    }

    private boolean isInUse() {
        return use;
    }

    private HttpParam next;

    public static HttpParam obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                HttpParam m = sPool;
                sPool = m.next;
                m.next = null;
                sPoolSize--;
                return m;
            }
        }
        return new HttpParam();
    }

    /**
     * 回收
     */
    public void recycle() {
        if (isInUse()) {
            return;
        }
        use = false;
        clear();

        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }
}
