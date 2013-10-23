package com.nttuyen.android.base.service;

import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.service.observer.ServiceObserver;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/6/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public abstract class AsyncRestWithCacheService<Param, Result> extends BaseService<Param, Result> implements ServiceObserver {
    protected Param param;

    protected final Class<?> type;

    protected AsyncService<Param, Result> fetchFromCacheService;
    protected AsyncService<Param, Request> createRequestService;
    protected AsyncService<Request, Result> fetchFromRestService;
    protected AsyncService<Result, Boolean> storeToCacheService;

    public AsyncRestWithCacheService(Class<?> type) {
        this.type = type;

        this.fetchFromCacheService = new AsyncService<Param, Result>() {
            @Override
            protected Result execute(Param param) {
                try {
                    this.result = fetchFromCache(param);
                } catch (ServiceException e) {
                    this.exception = e;
                    this.result = null;
                }
                return this.result;
            }
        };
        this.createRequestService = new AsyncService<Param, Request>() {
            @Override
            protected Request execute(Param param) {
                try {
                    this.result = createRequest(param);
                } catch (ServiceException e) {
                    this.exception = e;
                    this.result = null;
                }
                return this.result;
            }
        };
        this.fetchFromRestService  = new AsyncRestService<Result>(this.type) {
            @Override
            protected boolean checkSuccess(JSONObject json) throws JSONException {
                return checkRestSuccess(json);
            }

            @Override
            protected String getMessage(JSONObject json) throws JSONException {
                return getErrorMessage(json);
            }

            @Override
            protected String getJsonDataField() {
                return AsyncRestWithCacheService.this.getJsonDataField();
            }

            @Override
            protected void processMeta(JSONObject json) {
                AsyncRestWithCacheService.this.processMeta(json);
            }
        };
        this.storeToCacheService  = new AsyncService<Result, Boolean>() {
            @Override
            protected Boolean execute(Result result) {
                try {
                    this.result = storeToCache(result);
                } catch (ServiceException e){
                    this.exception = e;
                    this.result = null;
                }
                return this.result;
            }
        };

        //Register observer
        this.fetchFromCacheService.registerObserver(this);
        this.createRequestService.registerObserver(this);
        this.fetchFromRestService.registerObserver(this);
        this.storeToCacheService.registerObserver(this);
    }

    protected abstract Result fetchFromCache(Param param) throws ServiceException;
    protected abstract boolean storeToCache(Result result) throws ServiceException;
    protected abstract Request createRequest(Param param) throws ServiceException;
    protected abstract boolean checkRestSuccess(JSONObject json) throws JSONException;
    protected abstract String getErrorMessage(JSONObject json) throws JSONException;
    protected abstract String getJsonDataField();
    protected abstract void processMeta(JSONObject json);

    @Override
    public void init(Param param) {
        this.param = param;
    }


    @Override
    public void start() {
        this.fetchFromCacheService.init(this.param);
        this.fetchFromCacheService.start();
    }

    @Override
    public void onReady(Service service) {
//        if(service == this.fetchFromCacheService) {
//            for(ServiceObserver observer : this.observers) {
//                observer.onReady(this);
//            }
//        } else if(service == this.fetchFromRestService) {
//            for(ServiceObserver observer : this.observers) {
//                observer.onChange(this);
//            }
//        }
    }

    @Override
    public void onChange(Service service) {}

    @Override
    public void onInit(Service service) {}

    @Override
    public void onStart(Service service) {
        if(service == this.fetchFromCacheService) {
            for(ServiceObserver observer : this.observers) {
                observer.onStart(this);
            }
        }
    }

    @Override
    public void onCancel(Service service) {}

    @Override
    public void onFinish(Service service) {
        if(service == this.fetchFromCacheService) {
            try {
                this.result = this.fetchFromCacheService.get();
                if(this.result != null) {
                    for(ServiceObserver observer : this.observers) {
                        observer.onReady(this);
                    }
                }
            } catch (ServiceException e) {
                this.exception = e;
                this.result = null;
            }

            this.createRequestService.init(this.param);
            this.createRequestService.start();

        } else if(service == this.createRequestService) {
            try {
                Request request = this.createRequestService.get();
                this.fetchFromRestService.init(request);
                this.fetchFromRestService.start();
            } catch (ServiceException e) {
                if(this.result == null) {
                    this.exception = e;
                    this.result = null;

                    for(ServiceObserver observer : this.observers) {
                        observer.onReady(this);
                        observer.onFinish(this);
                    }
                } else {
                    for(ServiceObserver observer : this.observers) {
                        observer.onFinish(this);
                    }
                }
            }

        } else if(service == this.fetchFromRestService) {
            try {
                Result r = this.fetchFromRestService.get();
                if(r != null) {
                    if(this.result == null) {
                        this.result = r;
                        for(ServiceObserver observer : this.observers) {
                            observer.onReady(this);
                        }
                    } else {
                        this.result = r;
                        for(ServiceObserver observer : this.observers) {
                            observer.onChange(this);
                        }
                    }

                    this.storeToCacheService.init(this.result);
                    this.storeToCacheService.start();
                }
            } catch (ServiceException e) {
                if(this.result == null) {
                    this.exception = e;
                    this.result = null;

                    for(ServiceObserver observer : this.observers) {
                        observer.onReady(this);
                        observer.onFinish(this);
                    }
                } else {
                    for(ServiceObserver observer : this.observers) {
                        observer.onFinish(this);
                    }
                }
            }
        } else if(service.equals(this.storeToCacheService)) {
            for(ServiceObserver observer : this.observers) {
                observer.onFinish(this);
            }
        }
    }
}
