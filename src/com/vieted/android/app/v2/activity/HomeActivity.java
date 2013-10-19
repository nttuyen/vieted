package com.vieted.android.app.v2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.nttuyen.android.base.mvc.Model;
import com.nttuyen.android.base.widget.ActionBar;
import com.vieted.android.app.R;
import com.vieted.android.app.activity.MyCourseActivity;
import com.vieted.android.app.activity.ProfileActivity;
import com.vieted.android.app.v1.activity.ListCourseActivity;
import com.vieted.android.app.v2.model.VoidModel;

/**
 * @author nttuyen266@gmail.com
 */
public class HomeActivity extends BaseActivity {
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
	}

	@Override
	protected Model getModel() {
		return new VoidModel();
	}

	protected void initBodyView(Object... params) {
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
