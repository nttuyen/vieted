package com.vieted.android.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.androidteam.base.listener.RestAsyncTaskListener;
import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.utils.ActivityUtils;
import com.androidteam.base.widget.ActionBar;
import com.vieted.android.app.R;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/24/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractLessonActivity extends YouTubeFailureRecoveryActivity implements RestAsyncTaskListener<RestAsyncTask>, View.OnClickListener {
    protected ProgressDialog progressDialog;
    protected RestAsyncTask mainTask;

    protected ActionBar actionBar;
    protected LinearLayout bodyLayout;

    protected Button lessonButtonLecture;
    protected Button lessonButtonExercise;
    protected Button lessonButtonMemory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_global);

        this.actionBar = (ActionBar)this.findViewById(R.id.actionbar);
        this.actionBar.setTitle("Lesson");
        bodyLayout = (LinearLayout) findViewById(R.id.bodyLayout);
        this.setHomeBackAction();
        this.actionBar.addAction(new ActionBar.IntentAction(this, new Intent(this, HomeActivity.class), R.drawable.button_home));

        this.lessonButtonLecture = (Button)this.findViewById(R.id.lessonButtonLecture);
        this.lessonButtonExercise = (Button)this.findViewById(R.id.lessonButtonExercise);
        this.lessonButtonMemory = (Button)this.findViewById(R.id.lessonButtonMemory);
        this.lessonButtonLecture.setOnClickListener(this);
        this.lessonButtonExercise.setOnClickListener(this);
        this.lessonButtonMemory.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    protected void setHomeBackAction() {
        this.actionBar.setHomeAction(new ActionBar.Action() {
            @Override
            public int getDrawable() {
                return R.drawable.vieted_back_new;
            }

            @Override
            public void performAction(View view) {
                AbstractLessonActivity.this.onBackPressed();
            }
        });
    }

    protected void setTextHeader(String txtHeader) {
        if (txtHeader != null)
            this.actionBar.setTitle(txtHeader);
    }

    protected void setLayoutBody(int idBody) {
        bodyLayout.removeAllViews();
        bodyLayout.addView(getLayoutInflater().inflate(idBody, null));
    }

    protected abstract void handleGetSuccess(RestAsyncTask task);

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
    public void onTaskProgress(RestAsyncTask task) {}

    @Override
    public void onTaskSuccess(RestAsyncTask task) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        handleGetSuccess(task);
    }

    @Override
    public void onTaskFailure(RestAsyncTask data, Exception exception) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        this.processError(exception);
    }

    @Override
    public void onTaskFailure(RestAsyncTask data, String message) {
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

    @Override
    public void onClick(View view) {
        if(view == this.lessonButtonLecture) {

        } else if(view == this.lessonButtonExercise) {
            Intent intent = new Intent(this, LessonExerciseActivity.class);
            startActivity(intent);
            return;
        } else if(view == this.lessonButtonMemory) {

        }
    }
}
