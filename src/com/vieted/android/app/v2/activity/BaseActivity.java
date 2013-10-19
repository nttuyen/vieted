package com.vieted.android.app.v2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import com.nttuyen.android.base.Callback;
import com.nttuyen.android.base.mvc.Events;
import com.nttuyen.android.base.mvc.Model;
import com.nttuyen.android.base.mvc.Presenter;
import com.nttuyen.android.base.mvc.RestModel;
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
			//Show loading
			Events.on(model, Model.ON_PROCESS_START, new Callback() {
				@Override
				public void execute(Object... params) {
					//Model m = (Model)params[0];
					//String process = (String)params[1];
					//if(process == RestModel.PROCESS_HTTP_REQUEST) {
						contextHelper.showLoading();
					//}
				}
			});

			Events.on(model, Model.ON_PROCESS_COMPLETED, new Callback() {
				@Override
				public void execute(Object... params) {
					//Model m = (Model)params[0];
					//String process = (String)params[1];
					//if(process == RestModel.PROCESS_HTTP_REQUEST) {
						contextHelper.dismissLoading();
						//TODO: Should call onModelUpdate here or raise both ON_PROCESS_COMPLETED and ON_CHANGE event
						onModelUpdate();
						showBodyView();
					//}
				}
			});
			Events.on(model, Model.ON_CHANGE, new Callback() {
				@Override
				public void execute(Object... params) {
					onModelUpdate();
				}
			});

			Events.on(model, Model.ON_PROCESS_ERROR, new Callback() {
				@Override
				public void execute(Object... params) {
					//Model m = (Model)params[0];
					//String process = (String)params[1];
					String errorType = (String)params[2];
					String errorMessage = (String)params[3];

					//if(process == RestModel.PROCESS_HTTP_REQUEST) {
						contextHelper.dismissLoading();
						contextHelper.showErrDialog(errorType, errorMessage);
					//}
				}
			});

			//TODO: should we call model.fetch() here
			//We should let to concrete activity call it onStart()
			model.fetch();
		}
	}

	/**
	 * Update view when model change
	 */
	protected void onModelUpdate() {
		throw new UnsupportedOperationException();
	}

	protected void showBodyView() {
		View view = this.getView();
		if(view != null) {
			this.bodyLayout.removeAllViews();
			this.bodyLayout.addView(view);
		} else {
			throw new IllegalStateException("BodyView is not initialized");
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
}
