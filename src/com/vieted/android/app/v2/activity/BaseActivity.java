package com.vieted.android.app.v2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.nttuyen.android.base.mvc.EventListener;
import com.nttuyen.android.base.mvc.Events;
import com.nttuyen.android.base.mvc.Model;
import com.nttuyen.android.base.mvc.Presenter;
import com.nttuyen.android.base.utils.UIContextHelper;
import com.nttuyen.android.base.widget.ActionBar;
import com.vieted.android.app.R;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class BaseActivity extends FragmentActivity implements Presenter {
	protected ActionBar actionBar;
	protected LinearLayout bodyLayout;
	protected View bodyView;

	protected final UIContextHelper contextHelper = new UIContextHelper(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_global);

		this.actionBar = (ActionBar)this.findViewById(R.id.actionbar);
		this.actionBar.setTitle("Home");
		bodyLayout = (LinearLayout) findViewById(R.id.bodyLayout);
		this.setHomeNothingAction();
		this.actionBar.addAction(new ActionBar.IntentAction(this, new Intent(this, HomeActivity.class), R.drawable.vieted_icon));

		this.bodyView = null;
	}

	@Override
	protected void onStart() {
		super.onStart();

		final Model model = this.getModel();
		if(model != null) {
			//Register all event listener
			this.registerAllEvents();

			//TODO: should we call model.fetch() here
			//We should let to concrete activity call it onStart()
			model.fetch();
		}
	}

	protected void showBodyView() {
		View view = this.getView();
		if(view != null) {
			this.bodyLayout.removeAllViews();
			this.bodyLayout.addView(view);
		} else {
			contextHelper.showErrDialog("Error", "BodyView is not initialized");
		}
	}

	@Override
	public View getView() {
		return this.bodyView;
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
				BaseActivity.this.onBackPressed();
			}
		});
	}

	protected void setTextHeader(String txtHeader) {
		if (txtHeader != null)
			this.actionBar.setTitle(txtHeader);
	}

	protected void go(Class<? extends Activity> next) {
		this.go(next, null);
	}

	protected void go(Class<? extends Activity> next, Bundle extra) {
		Intent intent = new Intent(this, next);
		if(extra != null) {
			intent.putExtras(extra);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right,
				R.anim.slide_out_left);
	}


	//Event
	/**
	 * Update view when model change
	 */
	@EventListener(event = Model.ON_CHANGE)
	public void onModelUpdate() {

	}
	@EventListener(event = Model.ON_PROCESS_START)
	public void onProcessStart() {
		contextHelper.showLoading();
	}
	@EventListener(event = Model.ON_PROCESS_COMPLETED)
	public void onProcessComplete() {
		contextHelper.dismissLoading();
		onModelUpdate();
		showBodyView();
	}
	@EventListener(event = Model.ON_PROCESS_ERROR)
	public void onProcessError(Model m, String process, int errorCode, String errorString, String errorMessage) {
		contextHelper.dismissLoading();
		contextHelper.showErrDialog(errorString, errorMessage);
	}

	protected void on(String event, String method) {
		Model model = this.getModel();
		if(model != null) {
			Events.on(model, event, method, this);
		}
	}
	protected void registerAllEvents() {
		Model model = this.getModel();
		if(model == null) {
			return;
		}

		Events.registerAllEvents(model, this);
	}
}
