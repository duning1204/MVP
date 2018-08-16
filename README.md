# MVP
只要对V层和M层进行特别封装


## V层
使用注解的方式创建P
```
@CreatePresenter(MainPresenter.class)
public class MainActivity extends BaseMVPActivity<MainPresenter> implements IMainView {
}
```
使用Loader管理P，可以再屏幕状态改变的情况下拉取同一个P，不会重复创建。P不能在onCreate()使用，使用P需要在onPresenterCreate()
```
@Override
protected void onPresenterCreate() {
        
}
```

## P层
增加了生命周期管理，使其生命周期与其绑定的V一致，防止内存泄漏
```
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public V getView() {
        checkViewAttached();
        return mView;
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new NullPointerException("View 已为空");
        }
    }

    /**
     * 取消，置空数据，防止内存泄露
     */
    @Override
    public void onDetach() {
        mView = null;
    }

}
```

## M层
使用ModelManager统一管理Model，通过Token类传递全类目，使用反射创建Model，并在其内部增加了缓存。
```
private static ArrayMap<String, IBaseModel> mCache = new ArrayMap<>();

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
```
