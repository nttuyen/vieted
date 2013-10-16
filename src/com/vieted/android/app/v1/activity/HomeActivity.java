package com.vieted.android.app.v1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.nttuyen.android.base.widget.ActionBar;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.service.ServicesHelpers;
import com.nttuyen.android.base.service.VoidService;
import com.vieted.android.app.R;
import com.vieted.android.app.activity.MyCourseActivity;
import com.vieted.android.app.activity.ProfileActivity;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 10:56 AM
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
        }, 0);

        ServicesHelpers.startService(new VoidService(), this, new Handler<Void>() {
            @Override
            public void handle(Void value) {
                initBodyView();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //TODO: nothing
    }

    private void initBodyView() {
        this.setLayoutBody(R.layout.activity_body_home);
        Button myCourseButton = (Button)findViewById(R.id.buttonMyCourse);
        myCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go(MyCourseActivity.class);
            }
        });
        Button profileButton = (Button) findViewById(R.id.buttonProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go(ProfileActivity.class);
            }
        });
        Button listCourseButton = (Button) findViewById(R.id.buttonListCourse);
        listCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go(ListCourseActivity.class);
            }
        });
    }
}
