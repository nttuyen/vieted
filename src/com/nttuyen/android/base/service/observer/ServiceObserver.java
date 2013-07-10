package com.nttuyen.android.base.service.observer;

import com.nttuyen.android.base.service.Service;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/1/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ServiceObserver {
    public void onReady(Service service);
    public void onChange(Service service);

    //TODO: this method
    public void onInit(Service service);
    public void onStart(Service service);
    public void onCancel(Service service);
    public void onFinish(Service service);
}
