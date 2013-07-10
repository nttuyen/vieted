package com.nttuyen.android.base.service.observer;

import android.content.Context;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServiceException;
import com.nttuyen.android.base.service.observer.ServiceObserver;
import com.nttuyen.android.base.utils.UIContextHelper;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/2/13
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericServiceObserver implements ServiceObserver {
    protected final Context context;
    protected final UIContextHelper contextHelper;

    protected Handler mainHandler;
    protected Handler onChangeHandler;

    public GenericServiceObserver(Context context) {
        this(context, null);
    }

    public GenericServiceObserver(Context context, Handler mainHandler) {
        this(context, mainHandler, null);
    }

    public GenericServiceObserver(Context context, Handler mainHandler, Handler onChangeHandler) {
        this.context = context;
        this.contextHelper = new UIContextHelper(this.context);
        this.mainHandler = mainHandler;
        this.onChangeHandler = onChangeHandler;
    }

    @Override
    public void onStart(Service service) {
        this.contextHelper.showLoading();
    }

    @Override
    public void onReady(Service service) {
        this.contextHelper.dismissLoading();
        try {
            Object result = service.get();
            if(this.mainHandler != null) {
                this.mainHandler.handle(result);
            }
        } catch (ServiceException e) {
            this.contextHelper.showErrDialog("Error", e.getMessage());
        }
    }

    @Override
    public void onChange(Service service) {
        this.contextHelper.dismissLoading();
        try {
            if(this.onChangeHandler != null) {
                this.onChangeHandler.handle(service.get());
            }
        } catch (ServiceException e) {}
    }

    @Override
    public void onInit(Service service) {}

    @Override
    public void onCancel(Service service) {}

    @Override
    public void onFinish(Service service) {}
}
