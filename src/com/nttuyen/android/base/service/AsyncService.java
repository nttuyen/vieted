package com.nttuyen.android.base.service;

import android.os.AsyncTask;
import com.nttuyen.android.base.service.observer.ServiceObserver;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/6/13
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AsyncService<Param, Result> extends BaseService<Param, Result> {
    protected Param param;
    @Override
    public void init(Param param) {
        this.param = param;
    }

    @Override
    public void start() {
        this.async.execute(this.param);
    }

    protected abstract Result execute(Param param) throws ServiceException;

    private AsyncTask<Param, Void, Result> async = new AsyncTask<Param, Void, Result>() {
        @Override
        protected Result doInBackground(Param... params) {
            try {
                 AsyncService.this.result = AsyncService.this.execute(params[0]);
            } catch (ServiceException e) {
                AsyncService.this.result = null;
                AsyncService.this.exception = e;
            }

            return AsyncService.this.result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            for (ServiceObserver observer : AsyncService.this.observers) {
                observer.onStart(AsyncService.this);
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
            for (ServiceObserver observer : AsyncService.this.observers) {
                observer.onReady(AsyncService.this);
                observer.onFinish(AsyncService.this);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            for (ServiceObserver observer : AsyncService.this.observers) {
                observer.onCancel(AsyncService.this);
            }
        }
    };
}
