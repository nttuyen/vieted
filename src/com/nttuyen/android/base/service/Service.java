package com.nttuyen.android.base.service;

import com.nttuyen.android.base.service.observer.ServiceObserver;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/14/13
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Service<Param, Result> {
    public void init(Param param);
    public void start();
    public void cancel();
    public Result get() throws ServiceException;

    public void registerObserver(ServiceObserver observer);
    public void unregisterObserver(ServiceObserver observer);
}
