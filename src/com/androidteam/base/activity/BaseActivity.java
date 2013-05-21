package com.androidteam.base.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import com.androidteam.base.listener.RestAsyncTaskListener;
import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.utils.ActivityUtils;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseActivity<Task extends RestAsyncTask> extends Activity implements RestAsyncTaskListener<Task> {
    protected ProgressDialog progressDialog;
    protected Task mainTask;

    protected abstract void handleGetSuccess(Task task);

    protected void showLoading(String message, boolean cancelable) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setOwnerActivity(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }
    protected void showLoading() {
        this.showLoading("Loading...", true);
    }

    @Override
    public void onTaskStart() {
        this.showLoading();
    }

    @Override
    public void onTaskProgress(Task task) {}

    @Override
    public void onTaskSuccess(Task task) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        handleGetSuccess(task);
    }

    @Override
    public void onTaskFailure(Task data, Exception exception) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        this.processError(exception);
    }

    @Override
    public void onTaskFailure(Task data, String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        ActivityUtils.showErrDialog(this, message);
    }

    protected void processError(Exception exception) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (exception instanceof IOException) {
            ActivityUtils.showErrDialog(this, "Connection failed.");
        } else if (exception instanceof JSONException) {
            ActivityUtils.showErrDialog(this, "Data format error.");
        } else {
            ActivityUtils.showErrDialog(this, exception.getMessage());
        }
    }
}
