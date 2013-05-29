package com.androidteam.base.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.androidteam.base.listener.RestAsyncTaskListener;
import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.utils.ActivityUtils;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/26/13
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContextHelper {
    private final Context context;
    private ProgressDialog progressDialog;

    public ContextHelper(Context context) {
        this.context = context;
        this.progressDialog = null;
    }

    public void showLoading() {
        this.showLoading("Loading...", true);
    }

    public void showLoading(String message, boolean cancelable) {
        progressDialog = new ProgressDialog(this.context);
        if(this.context instanceof Activity) {
            progressDialog.setOwnerActivity((Activity)this.context);
        }
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    /*public void showErrDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.context);
        dialog.setMessage(message).setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
    }*/
}
