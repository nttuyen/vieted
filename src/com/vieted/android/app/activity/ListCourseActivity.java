package com.vieted.android.app.activity;

import android.os.Bundle;

import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;
import com.vieted.android.app.task.GetCourseListTask;

public class ListCourseActivity extends VietEdBaseActivity{
	GetCourseListTask getCourseListTask ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setTextHeader("List Course");
		setLayoutBody(R.layout.activity_body_listcourse);
		this.getCourseListTask = new GetCourseListTask();
		//this.getCourseListTask.addParam(name, value)
	}

	@Override
	protected void handleGetSuccess(RestAsyncTask task) {
		// TODO Auto-generated method stub
		
	}

}
