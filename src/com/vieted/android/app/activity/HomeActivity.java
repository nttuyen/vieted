package com.vieted.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.androidteam.base.task.RestAsyncTask;
import com.nttuyen.android.base.widget.ActionBar;
import com.vieted.android.app.R;
import com.vieted.android.app.task.HomeTask;

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
                return R.drawable.vieted_icon;
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
                Intent intent = new Intent(HomeActivity.this, MyCourseActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        Button profileButton = (Button) findViewById(R.id.buttonProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			}
		});
        Button listCourseButton = (Button) findViewById(R.id.buttonListCourse);
        listCourseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, ListCourseActivity.class);
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
