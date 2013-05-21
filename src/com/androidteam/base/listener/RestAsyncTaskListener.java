package com.androidteam.base.listener;

import com.androidteam.base.task.RestAsyncTask;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RestAsyncTaskListener<Task extends RestAsyncTask> {
    void onTaskStart();
    void onTaskProgress(Task task);
    void onTaskSuccess(Task task);
    void onTaskFailure(Task data, Exception exception);
    void onTaskFailure(Task data, String message);
}
