package com.nttuyen.android.base.service;

import com.nttuyen.android.base.service.observer.ServiceObserver;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/6/13
 * Time: 8:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class VoidService extends BaseService<Void, Void> {
    @Override
    public void init(Void param) {}

    @Override
    public void start() {
        for (ServiceObserver observer : this.observers) {
            observer.onStart(this);
            observer.onReady(this);
            observer.onFinish(this);
        }
    }
}
