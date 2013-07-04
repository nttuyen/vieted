package com.nttuyen.android.base.service;

import java.util.LinkedHashSet;
import java.util.Set;
public abstract class BaseService<Param, Result> implements Service<Param, Result> {
    protected Set<ServiceObserver> observers = new LinkedHashSet<ServiceObserver>();

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
