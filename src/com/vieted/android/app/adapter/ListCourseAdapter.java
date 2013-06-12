package com.vieted.android.app.adapter;

import java.util.ArrayList;

import com.vieted.android.app.domain.Course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListCourseAdapter extends BaseAdapter{
	
	private ArrayList<Course> mListCourses;
	private LayoutInflater mLayoutInflater;
	
	

	public ListCourseAdapter(ArrayList<Course> mListCourses,
			Context context) {
		super();
		this.mListCourses = mListCourses;
		this.mLayoutInflater = mLayoutInflater;
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
		return null;
	}

}
