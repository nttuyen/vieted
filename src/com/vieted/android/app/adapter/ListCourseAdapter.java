package com.vieted.android.app.adapter;

import java.util.ArrayList;

import com.vieted.android.app.R;
import com.vieted.android.app.domain.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListCourseAdapter extends BaseAdapter{
	
	private ArrayList<Course> mListCourses;
	private LayoutInflater mLayoutInflater;
	
	

	public ListCourseAdapter(ArrayList<Course> mListCourses,
			Context context) {
		super();
		this.mListCourses = mListCourses;
		mLayoutInflater = LayoutInflater.from(context); 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListCourses.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mListCourses.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (convertView== null) 
			vi= mLayoutInflater.inflate(R.layout.item_list_course,null);
		TextView name = (TextView) vi.findViewById(R.id.listCoureName);
		TextView teacher = (TextView) vi.findViewById(R.id.listCourseTeacher);
		TextView level = (TextView) vi.findViewById(R.id.listCourseLevel);
		
		name.setText(mListCourses.get(position).getName());
		teacher.setText(mListCourses.get(position).getTeacher());
		level.setText(mListCourses.get(position).getLevel());
		return convertView;
	}

}
