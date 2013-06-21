package com.vieted.android.app.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.utils.JsonUtils;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.ListCourseAdapter;
import com.vieted.android.app.domain.Course;
import com.vieted.android.app.task.GetCourseListTask;

public class ListCourseActivity extends VietEdBaseActivity{
	RestAsyncTask getCourseListTask ;
	ArrayList<Course> mListCourses;
	
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
	public ArrayList<Course> getListCourses(JSONArray jsonArray){
		ArrayList<Course> listResult = new ArrayList<Course>();
		try {
			
			int length = jsonArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject temp = jsonArray.getJSONObject(i);
				String name = temp.getString("name");
				int level = temp.getInt("level");
				JSONObject staff = temp.getJSONObject("staff");
				String teacher = staff.getString("name");
				String url = temp.getString("url");
				Course tempCourse = new Course();
				tempCourse.setLevel((byte) level);
				tempCourse.setName(name);
				tempCourse.setUrl(url);
				tempCourse.setTeacher(teacher);
				listResult.add(tempCourse);
				
			}
			return listResult;
			
		} catch (Exception e) {
			// TODO: handle exception
			return listResult;
		}
		
		
		
	}
	
	
	
	public void initbodyView(){
		setLayoutBody(R.layout.activity_body_listcourse);
		mListCourses = new ArrayList<Course>();
		JSONArray result = JsonUtils.getJsonArrayResult(this.mainTask.getLastResult());
		mListCourses = getListCourses(result);
		
		ListView listView = (ListView) findViewById(R.id.listCourseTotal);
		ListAdapter listAdapter = new ListCourseAdapter(mListCourses, ListCourseActivity.this);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String url = mListCourses.get(arg2).getUrl();
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
