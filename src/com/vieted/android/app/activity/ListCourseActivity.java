package com.vieted.android.app.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.ListCourseAdapter;
import com.vieted.android.app.domain.Course;
import com.vieted.android.app.task.GetCourseListTask;

public class ListCourseActivity extends VietEdBaseActivity{
	GetCourseListTask getCourseListTask ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setTextHeader("List Course");
		this.setHomeBackAction();
		this.mainTask = new GetCourseListTask();
		this.mainTask.setRestAsyncTaskListener(this);
		this.mainTask.execute();
	}
	
	public void initbodyView(){
		setLayoutBody(R.layout.activity_body_listcourse);
		ArrayList<Course> mListCourses = new ArrayList<Course>();
		ListView listView = (ListView) findViewById(R.id.listCourseTotal);
		for (int i = 0;i<10;i++){
			Course temp = new Course();
			temp.setName("Course "+i);
			temp.setTeacher("Teacher "+i);
			temp.setLevel((byte) 0);
			mListCourses.add(temp);
			
		}
		ListAdapter listAdapter = new ListCourseAdapter(mListCourses, ListCourseActivity.this);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ListCourseActivity.this,ListUnitActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void handleGetSuccess(RestAsyncTask task) {
		// TODO Auto-generated method stub
		if(task == mainTask){
			initbodyView();
		}
		
	}

}
