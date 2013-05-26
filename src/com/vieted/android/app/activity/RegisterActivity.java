package com.vieted.android.app.activity;

import android.os.Bundle;

import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;

public class RegisterActivity extends VietEdBaseActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setTextHeader("Register");
		this.setLayoutBody(R.layout.activity_register_account);
		this.setHomeBackAction();
	}
	
	@Override
	protected void handleGetSuccess(RestAsyncTask task) {
		// TODO Auto-generated method stub
		
	}

}
