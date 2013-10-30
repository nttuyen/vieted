package com.vieted.android.v2.activity;

import android.view.View;
import com.nttuyen.android.umon.core.mvc.Model;
import com.nttuyen.android.umon.widget.UIContextHelper;

/**
 * @author nttuyen266@gmail.com
 */
public class HomeActivity extends BaseActivity {
	@Override
	protected void onStart() {
		super.onStart();

		UIContextHelper contextHelper = new UIContextHelper(this);
		contextHelper.showLoading();
	}

	@Override
	public View getView() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Model getModel() {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
