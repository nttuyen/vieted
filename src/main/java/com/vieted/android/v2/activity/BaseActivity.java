package com.vieted.android.v2.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.nttuyen.android.umon.core.mvc.Events;
import com.nttuyen.android.umon.core.mvc.Model;
import com.nttuyen.android.umon.core.mvc.ModelEventListener;
import com.nttuyen.android.umon.core.mvc.Presenter;
import com.nttuyen.android.umon.core.ui.AnnotationFragmentActivity;
import com.nttuyen.android.umon.core.ui.UIContextHelper;
import com.nttuyen.android.umon.core.ui.UIEvents;
import com.nttuyen.android.umon.core.ui.UIOnclick;
import com.nttuyen.android.umon.widget.ActionBar;
import com.vieted.android.v2.R;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class BaseActivity extends AnnotationFragmentActivity {
	protected ActionBar actionBar;
	protected LinearLayout bodyLayout;
	protected View bodyView;

	protected final UIContextHelper contextHelper = new UIContextHelper(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		this.actionBar = (ActionBar)this.findViewById(R.id.actionbar);
		this.actionBar.setHomeLogo(R.drawable.vieted_icon);
		this.actionBar.setDisplayHomeAsUpEnabled(true);
		this.actionBar.setTitle("Home");
		bodyLayout = (LinearLayout) findViewById(R.id.bodyLayout);

		this.bodyView = null;
	}

	@Override
	protected void onStart() {
		super.onStart();
		final Model model = this.getModel();
		if(model != null) {
			//Register all event listener
			this.registerModelEvents();

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
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);
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
	@ModelEventListener(event = Model.ON_CHANGE)
	public void onModelUpdate() {

	}
	@ModelEventListener(event = Model.ON_PROCESS_START)
	public void onProcessStart() {
		contextHelper.showLoading();
	}
	@ModelEventListener(event = Model.ON_PROCESS_COMPLETED)
	public void onProcessComplete() {
		contextHelper.dismissLoading();
		onModelUpdate();
		showBodyView();
	}
	@ModelEventListener(event = Model.ON_PROCESS_ERROR)
	public void onProcessError(Model m, String process, int errorCode, String errorString, String errorMessage) {
		contextHelper.dismissLoading();
		contextHelper.showErrDialog(errorString, errorMessage);
	}
}
