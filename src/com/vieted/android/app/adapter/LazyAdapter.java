package com.vieted.android.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.vieted.android.app.R;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 6:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class LazyAdapter extends BaseAdapter {
    private Activity activity;
    private List<Map<String, String>> data;
    private static LayoutInflater inflater=null;

    public LazyAdapter(Activity a, List<Map<String, String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView number = (TextView)vi.findViewById(R.id.listRowNumberText);
        TextView name = (TextView)vi.findViewById(R.id.listRowName);
        TextView percentCompleted = (TextView)vi.findViewById(R.id.listRowPercentComplete);
        TextView score =(TextView)vi.findViewById(R.id.listRowScore); // thumb image

        Map<String, String> course = this.data.get(position);
        number.setText("" + position);
        name.setText(course.get("NAME"));
        percentCompleted.setText(course.get("PERCENT_COMPLETED"));
        score.setText(course.get("SCORE"));
        return vi;
    }
}
