package com.vieted.android.app.activity;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.utils.JsonUtils;
import com.google.android.youtube.player.internal.u;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.ListCourseAdapter;
import com.vieted.android.app.adapter.RatingAdapter;
import com.vieted.android.app.domain.BaseObject;
import com.vieted.android.app.domain.Unit;
import com.vieted.android.app.task.GetCourseListTask;
import com.vieted.android.app.task.GetUnitTask;

public class ListUnitActivity extends VietEdBaseActivity {
	ArrayList<Unit> mListUnits = new ArrayList<Unit>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setTextHeader("List Unit");
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
		JSONObject tempJson = this.mainTask.getLastResult();
		JSONObject result = JsonUtils.getJsonObjectResult(tempJson);
		//int length = result.length();sy
	
		try {
			
			JSONObject syllabus = result.getJSONObject("syllabus");
			JSONArray units = syllabus.getJSONArray("units");
			int length = units.length();
			for(int i = 0;i<length;i++){
				JSONObject unitTemp = units.getJSONObject(i);
				String name = unitTemp.getString("name");
				String urlUnit = unitTemp.getString("url");
				int percent = unitTemp.getInt("progress");
				float star = (float) unitTemp.getDouble("stars");
				Unit unit = new Unit();
				unit.setName(name);
				unit.setPercentCompleted(percent);
				unit.setScored(star);
				unit.setUrl(urlUnit);
				mListUnits.add(unit);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		ListView listView = (ListView) findViewById(R.id.listCourseTotal);
		
		
		ArrayList<BaseObject> lstBaseObjects = new ArrayList<BaseObject>();
		
		for (Unit unit : mListUnits) {
			BaseObject temp = new BaseObject();
			temp.setName(unit.getName());
			temp.setPara2(unit.getPercentCompleted()+"%");
			temp.setPara3(unit.getScored());
			temp.setUrl(unit.getUrl());
			lstBaseObjects.add(temp);
			
			
		}
		ListAdapter listAdapter = new RatingAdapter(lstBaseObjects,	ListUnitActivity.this);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String url = mListUnits.get(arg2).getUrl();
			
				Intent intent = new Intent(ListUnitActivity.this,ListLessonActivity.class);
				intent.putExtra("url",url);
				startActivity(intent);
				
			
				
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
