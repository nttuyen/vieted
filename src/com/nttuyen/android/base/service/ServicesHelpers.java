package com.nttuyen.android.base.service;

import android.content.Context;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.service.observer.GenericServiceObserver;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/1/13
 * Time: 11:08 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class ServicesHelpers {

    //Use when run background service
    public static Service startService(Service service) {
        service.start();
        return service;
    }
    public static Service startService(Service service, Context context) {
        return startService(service, context, null);
    }
    public static Service startService(Service service, Context context, Handler mainHandler) {
        return startService(service, context, mainHandler, null);
    }
    public static Service startService(Service service, Context context, Handler mainHandler, Handler onChangeHander) {
        service.registerObserver(new GenericServiceObserver(context, mainHandler, onChangeHander));
        service.start();
        return service;
    }
}
