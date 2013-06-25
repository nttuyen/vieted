package com.vieted.android.app.adapter;

import java.util.ArrayList;

import com.vieted.android.app.R;
import com.vieted.android.app.domain.BaseObject;
import com.vieted.android.app.domain.Unit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingAdapter extends BaseAdapter{
	ArrayList<BaseObject> objList;
	private LayoutInflater mLayoutInflater;

	
	public RatingAdapter(ArrayList<BaseObject> objList,
			Context context) {
		super();
		this.objList = objList;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objList.size();
	}

	@Override
	public BaseObject getItem(int arg0) {
		// TODO Auto-generated method stub
		return objList.get(arg0);
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
			vi= mLayoutInflater.inflate(R.layout.item_row_rating_bar,null);
		TextView name = (TextView) vi.findViewById(R.id.listRateText1);
		TextView percent = (TextView) vi.findViewById(R.id.listRateText2);
		RatingBar ratingBar = (RatingBar) vi.findViewById(R.id.ratingbar);
		
		name.setText(objList.get(position).getName());
		percent.setText(objList.get(position).getPara2());
		ratingBar.setClickable(false);
		ratingBar.setRating(objList.get(position).getPara3());
		return vi;
	}

}
