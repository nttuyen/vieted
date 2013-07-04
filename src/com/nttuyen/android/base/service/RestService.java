package com.nttuyen.android.base.service;

import android.os.AsyncTask;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.utils.RequestHelper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public abstract class RestService<Result> extends BaseService<Request, Result> {
    protected static final HttpClient httpClient = new DefaultHttpClient();
    protected Request request;
    protected ServiceException exception = null;
    protected Result result = null;

    private boolean hasLocal = false;

    @Override
    public void init(Request request) {
        this.request = request;
    }

    @Override
    public void start() {
        for(ServiceObserver observer : this.observers) {
            observer.onStart(this);
        }
        this.cacheTask.execute(request);
    }

    @Override
    public void cancel() {
        super.cancel();
        for(ServiceObserver observer : RestService.this.observers) {
            observer.onCancel(RestService.this);
        }
        this.mainAsyncTask.cancel(true);
    }

    @Override
    public Result get() throws ServiceException {
        if(this.exception != null) throw this.exception;

        if(this.result == null) {
            try {
                this.result = this.mainAsyncTask.get();
            } catch (InterruptedException e) {
                this.exception = new ServiceException("Service was canceled!", e);
            } catch (ExecutionException e) {
                this.exception = new ServiceException("Service error on execute!", e);
            }
            throw this.exception;
        }
        return this.result;
    }

    protected abstract boolean checkSuccess(JSONObject json) throws JSONException;
    protected abstract String getMessage(JSONObject json) throws JSONException;
    protected abstract Result getData(JSONObject json) throws JSONException;

    protected Result getCache(Request request) throws ServiceException {
        return null;
    }

    AsyncTask<Request, Void, Result> cacheTask = new AsyncTask<Request, Void, Result>() {
        @Override
        protected Result doInBackground(Request... requests) {
            try {
                RestService.this.result = getCache(requests[0]);
            } catch (ServiceException ex) {
                RestService.this.exception = ex;
                RestService.this.result = null;
            }
            return RestService.this.result;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            boolean sync = false;
            if(result != null) {
                hasLocal = true;
                for(ServiceObserver observer : RestService.this.observers) {
                    observer.onReady(RestService.this);
                }
                //TODO: check for update
                sync = true;
            } else {
                sync = true;
            }
            if(sync) {
                mainAsyncTask.execute(request);
            }
        }
    };

    AsyncTask<Request, Void, Result> mainAsyncTask = new AsyncTask<Request, Void, Result>() {
        @Override
        protected Result doInBackground(Request... requests) {
            try {
                Request req = requests[0];
                String response = RequestHelper.execute(httpClient, request);
                JSONObject json = new JSONObject(response);
                if(!checkSuccess(json)) {
                    String message = getMessage(json);
                    RestService.this.exception = new ServiceException(message);
                }
                RestService.this.result = getData(json);
                return RestService.this.result;
            } catch (IOException e) {
                RestService.this.exception = new ServiceException("Error on execution request", e);
            } catch (JSONException e) {
                RestService.this.exception = new ServiceException("Data format error!", e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            for(ServiceObserver observer : RestService.this.observers) {
                if(hasLocal) {
                    observer.onChange(RestService.this);
                } else {
                    observer.onReady(RestService.this);
                }
            }
        }
    };
}
