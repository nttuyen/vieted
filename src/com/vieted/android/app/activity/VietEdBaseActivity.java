package com.vieted.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.androidteam.base.activity.BaseActivity;
import com.androidteam.base.task.RestAsyncTask;
import com.nttuyen.android.base.widget.ActionBar;
import com.vieted.android.app.R;

/**
 * Created with IntelliJ IDEA. User: nttuyen Date: 5/21/13 Time: 1:54 PM To
 * change this template use File | Settings | File Templates.
 */
@Deprecated
public abstract class VietEdBaseActivity extends BaseActivity<RestAsyncTask> {
	protected ActionBar actionBar;
	protected LinearLayout bodyLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_global);

        this.actionBar = (ActionBar)this.findViewById(R.id.actionbar);
        this.actionBar.setTitle("Home");
        bodyLayout = (LinearLayout) findViewById(R.id.bodyLayout);
        this.setHomeNothingAction();
        //this.actionBar.addAction(new ActionBar.IntentAction(this, new Intent(this, HomeActivity.class), R.drawable.vieted_icon));
    }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
	}

	protected void setHomeNothingAction() {
		this.actionBar.setHomeAction(new ActionBar.Action() {
			@Override
			public int getDrawable() {
				return R.drawable.vieted_icon;
			}

			@Override
			public void performAction(View view) {
			}
		});
	}

	protected void setHomeBackAction() {
		this.actionBar.setHomeAction(new ActionBar.Action() {
			@Override
			public int getDrawable() {
				return R.drawable.vieted_back_new;
			}

			@Override
			public void performAction(View view) {
				VietEdBaseActivity.this.onBackPressed();
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
}
