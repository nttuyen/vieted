package com.vieted.android.app.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.ListCourseAdapter;
import com.vieted.android.app.adapter.RatingAdapter;
import com.vieted.android.app.domain.Unit;
import com.vieted.android.app.task.GetCourseListTask;

public class ListUnitActivity extends VietEdBaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setTextHeader("List Unit");
		this.setHomeBackAction();
		this.mainTask = new GetCourseListTask();
		this.mainTask.setRestAsyncTaskListener(this);
		this.mainTask.execute();
	}

	public void initbodyView() {
		setLayoutBody(R.layout.activity_body_listcourse);
		ArrayList<Unit> mListUnits = new ArrayList<Unit>();
		ListView listView = (ListView) findViewById(R.id.listCourseTotal);
		for (int i = 0; i < 10; i++) {
			Unit temp = new Unit();
			temp.setName("Unit " + i);
			temp.setPercentCompleted((byte)i);
			temp.setScored((byte) i);
			mListUnits.add(temp);

		}
		ListAdapter listAdapter = new RatingAdapter(mListUnits,	ListUnitActivity.this);
		listView.setAdapter(listAdapter);
	}

	@Override
	protected void handleGetSuccess(RestAsyncTask task) {
		// TODO Auto-generated method stub
		if (task == mainTask) {
			initbodyView();
		}

	}

}
