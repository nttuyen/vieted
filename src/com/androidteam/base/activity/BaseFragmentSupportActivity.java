package com.androidteam.base.activity;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import com.androidteam.base.listener.RestAsyncTaskListener;
import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.utils.ActivityUtils;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/5/13
 * Time: 10:42 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseFragmentSupportActivity<Task extends RestAsyncTask> extends FragmentActivity implements RestAsyncTaskListener<Task> {
    protected final UIContextHelper contextHelper;
    protected Task mainTask;

    protected BaseFragmentSupportActivity() {
        super();
        this.contextHelper = new UIContextHelper(this);
    }
    protected BaseFragmentSupportActivity(UIContextHelper contextHelper) {
        super();
        this.contextHelper = contextHelper;
    }

    protected abstract void handleGetSuccess(Task task);

    @Deprecated
    protected void showLoading(String message, boolean cancelable) {
        this.contextHelper.showLoading(message, cancelable);
    }
    @Deprecated
    protected void showLoading() {
        this.contextHelper.showLoading();
    }

    @Override
    public void onTaskStart() {
        this.contextHelper.showLoading();
    }

    @Override
    public void onTaskProgress(Task task) {}

    @Override
    public void onTaskSuccess(Task task) {
        this.contextHelper.dismissLoading();
        handleGetSuccess(task);
    }

    @Override
    public void onTaskFailure(Task data, Exception exception) {
        this.contextHelper.dismissLoading();
        this.processError(exception);
    }

    @Override
    public void onTaskFailure(Task data, String message) {
        this.contextHelper.dismissLoading();
        ActivityUtils.showErrDialog(this, message);
    }

    protected void processError(Exception exception) {
        this.contextHelper.dismissLoading();

        if (exception instanceof IOException) {
            ActivityUtils.showErrDialog(this, "Connection failed.");
        } else if (exception instanceof JSONException) {
            ActivityUtils.showErrDialog(this, "Data format error.");
        } else {
            ActivityUtils.showErrDialog(this, exception.getMessage());
        }
    }
}
