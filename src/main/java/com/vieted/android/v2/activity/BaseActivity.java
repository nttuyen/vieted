package com.vieted.android.v2.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.nttuyen.android.umon.core.mvc.Presenter;
import com.vieted.android.v2.R;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class BaseActivity extends FragmentActivity implements Presenter {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
}
