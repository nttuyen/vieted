package com.androidteam.base.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/19/13
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ActivityUtils {
    /**
     * Display a simple alert dialog with the given text and title.
     * @param context Android context in which the dialog should be displayed
     * @param title Alert dialog title
     * @param text Alert dialog message
     */
    public static void showAlert(Context context, String title, String text) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(text);
        alertBuilder.create().show();
    }

    public static void showErrDialog(Context context, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(message).setTitle("Warning..!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
    }

    public static void showAlertDialog(Context context, String message,
                                       String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(message).setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
    }
}
