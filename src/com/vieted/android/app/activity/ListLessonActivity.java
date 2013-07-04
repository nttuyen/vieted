package com.vieted.android.app.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.utils.JsonUtils;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.RatingAdapter;
import com.vieted.android.app.domain.BaseObject;
import com.vieted.android.app.domain.Lesson;
import com.vieted.android.app.domain.Unit;
import com.vieted.android.app.task.GetUnitTask;

public class ListLessonActivity  extends VietEdBaseActivity{
	ArrayList<Lesson> mArrayListLessons = new ArrayList<Lesson>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		this.setTextHeader("List Lesson");
		this.setHomeBackAction();
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		this.mainTask = new GetUnitTask(url,url);
		this.mainTask.setRestAsyncTaskListener(this);
		this.mainTask.execute();
	}
	
	public void initbodyView() {
		setLayoutBody(R.layout.activity_body_listcourse);
		TextView label1 = (TextView) findViewById(R.id.listLabel1);
		TextView label3 = (TextView) findViewById(R.id.listLabel3);
		label1.setText("Name");
		label3.setText("Scored");
		
		
		
		try {
			JSONObject tempJson = this.mainTask.getLastResult();
			JSONObject result = JsonUtils.getJsonObjectResult(tempJson);
			JSONArray lessonArray = result.getJSONArray("lessons");
			int length = lessonArray.length();
			for (int i = 0; i < length; i++) {
				JSONObject lessonJson = lessonArray.getJSONObject(i);
				String id = lessonJson.getString("id");
				String name = lessonJson.getString("name");
				int progress = lessonJson.getInt("progress");
				float stars = (float) lessonJson.getDouble("stars");
				String url = lessonJson.getString("url");
				Lesson lesson = new Lesson();
				
				lesson.setName(name);
				lesson.setScored(stars);
				lesson.setProgress(progress);
				lesson.setUrl(url);
				mArrayListLessons.add(lesson);
			}
			
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		ArrayList<BaseObject> lstBaseObjects = new ArrayList<BaseObject>();
		for (Lesson unit : mArrayListLessons) {
			BaseObject temp = new BaseObject();
			temp.setName(unit.getName());
			temp.setPara2(unit.getProgress()+"%");
			temp.setPara3(unit.getScored());
			temp.setUrl(unit.getUrl());
			lstBaseObjects.add(temp);
			
			
		}
		ListView listView = (ListView) findViewById(R.id.listCourseTotal);
		ListAdapter listAdapter = new RatingAdapter(lstBaseObjects,	ListLessonActivity.this);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//String url = mListUnits.get(arg2).getUrl();
			
				//Intent intent = new Intent(ListUnitActivity.this,ListLessonActivity.class);
				//intent.putExtra("url",url);
				//startActivity(intent);
				
			
				
			}
		});
	
	}

	@Override
	protected void handleGetSuccess(RestAsyncTask task) {
		// TODO Auto-generated method stub
		if (task == mainTask) {
			initbodyView();
		}
		
	}

}
