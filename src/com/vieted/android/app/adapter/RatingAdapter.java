package com.vieted.android.app.adapter;

import java.util.ArrayList;

import com.vieted.android.app.R;
import com.vieted.android.app.domain.Unit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingAdapter extends BaseAdapter{
	ArrayList<Unit> unitList;
	private LayoutInflater mLayoutInflater;

	
	public RatingAdapter(ArrayList<Unit> unitList,
			Context context) {
		super();
		this.unitList = unitList;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return unitList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return unitList.get(arg0);
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
		
		name.setText(unitList.get(position).getName());
		percent.setText(unitList.get(position).getPercentCompleted()*10+"%");
		ratingBar.setClickable(false);
		ratingBar.setRating((float)unitList.get(position).getScored()/2);
		
		return vi;
	}

}
