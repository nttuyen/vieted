package com.nttuyen.android.base.service;

import com.nttuyen.android.base.service.observer.ServiceObserver;

import java.util.LinkedHashSet;
import java.util.Set;
@Deprecated
public abstract class BaseService<Param, Result> implements Service<Param, Result> {
    protected Set<ServiceObserver> observers = new LinkedHashSet<ServiceObserver>();

    protected Result result = null;
    protected ServiceException exception = null;

    @Override
    public Result get() throws ServiceException {
        if(this.exception != null) {
            throw this.exception;
        }
        return this.result;
    }

    @Override
    public void cancel() {}

    @Override
    public void registerObserver(ServiceObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregisterObserver(ServiceObserver observer) {
        this.observers.remove(observer);
    }
}
