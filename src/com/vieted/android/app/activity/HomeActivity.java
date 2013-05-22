package com.vieted.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.widget.ActionBar;
import com.vieted.android.app.R;
import com.vieted.android.app.task.HomeTask;
import com.vieted.android.app.task.VoidTask;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class HomeActivity extends VietEdBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTextHeader("VietEd");
        this.actionBar.removeActionAt(0);
        this.actionBar.addAction(new ActionBar.Action() {
            @Override
            public int getDrawable() {
                return R.drawable.button_home;
            }

            @Override
            public void performAction(View view) {

            }
        });

        this.mainTask = new HomeTask();
        this.mainTask.setRestAsyncTaskListener(this);
        this.mainTask.execute();
    }

    private void initBodyView() {
        this.setLayoutBody(R.layout.activity_body_home);
        Button myCourseButton = (Button)findViewById(R.id.buttonMyCourse);
        myCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CourseListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        if(task == this.mainTask) {
            this.initBodyView();
        }
    }
}
