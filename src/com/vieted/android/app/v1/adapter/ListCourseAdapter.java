package com.vieted.android.app.v1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nttuyen.android.base.service.Service;
import com.vieted.android.app.R;
import com.vieted.android.app.v1.dto.Course;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListCourseAdapter extends VietEdListBaseAdapter<Course>{

    public ListCourseAdapter(Context context, Service service) {
        super(context, service);
    }

    @Override
    public long getItemId(int i) {
        return 0;
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

        name.setText(this.items.get(position).getName());
        teacher.setText(this.items.get(position).getStaff().getName());
        level.setText(" " + this.items.get(position).getLevel());
        return vi;
    }
}
