package com.vieted.android.app.v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServiceException;
import com.nttuyen.android.base.service.observer.ServiceObserver;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class VietEdListBaseAdapter<T> extends BaseAdapter {
    protected Service service;
    protected int count;
    protected List<T> items;
    protected LayoutInflater mLayoutInflater;

    public VietEdListBaseAdapter(Context context, Service service) {
        if(context != null) this.mLayoutInflater = LayoutInflater.from(context);
        this.service = service;
        if(this.service != null) {
            try {
                this.items = (List<T>)service.get();
                if(this.items == null) {
                    this.items = Collections.EMPTY_LIST;
                }
                this.count = this.items.size();
            } catch (ServiceException e) {
                this.items = Collections.EMPTY_LIST;
                this.count = 0;
            }

            this.service.registerObserver(new ServiceObserver() {
                @Override
                public void onReady(Service service) {
                    try {
    //                    boolean hasData = true;
    //                    if(VietEdListBaseAdapter.this.items == null) {
    //                        hasData = false;
    //                    }
                        VietEdListBaseAdapter.this.items = (List<T>)service.get();
                        if(VietEdListBaseAdapter.this.items == null) {
                            VietEdListBaseAdapter.this.items = Collections.EMPTY_LIST;
                        }
                    } catch (ServiceException e) {
                        VietEdListBaseAdapter.this.items = Collections.EMPTY_LIST;
                    }
                    VietEdListBaseAdapter.this.count = VietEdListBaseAdapter.this.items.size();
                    VietEdListBaseAdapter.this.notifyDataSetChanged();
                }

                @Override
                public void onChange(Service service) {
                    onReady(service);
                }
                @Override
                public void onInit(Service service) {}
                @Override
                public void onStart(Service service) {}
                @Override
                public void onCancel(Service service) {}
                @Override
                public void onFinish(Service service) {}
            });
        }
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public T getItem(int i) {
        return this.items.get(i);
    }
}
