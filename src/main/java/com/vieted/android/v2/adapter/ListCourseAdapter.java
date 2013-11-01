package com.vieted.android.v2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nttuyen.android.umon.core.mvc.Model;
import com.nttuyen.android.umon.core.mvc.ModelEventListener;
import com.nttuyen.android.umon.core.mvc.Presenter;
import com.nttuyen.android.umon.core.ui.GenericPresenter;
import com.nttuyen.android.umon.core.ui.ModelAdapter;
import com.vieted.android.v2.R;
import com.vieted.android.v2.model.CourseModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nttuyen266@gmail.com
 */
public class ListCourseAdapter extends ModelAdapter<CourseModel> {

	protected final LayoutInflater mLayoutInflater;
	Map<CourseModel, Presenter> presenters = new HashMap<CourseModel, Presenter>();

	public ListCourseAdapter(Context context, Model.Collection<CourseModel> models) {
		super(models);
		this.mLayoutInflater = context!= null ? LayoutInflater.from(context) : null;
	}

	@Override
	public Presenter getPresenter(int i, View convertView, ViewGroup viewGroup) {
		CourseModel course = this.models.get(i);
		if(presenters.containsKey(course)) {
			return presenters.get(course);
		}

		View vi = convertView;
		if (convertView== null)
			vi= mLayoutInflater.inflate(R.layout.list_item_course, null);
		TextView name = (TextView) vi.findViewById(R.id.listCoureName);
		TextView teacher = (TextView) vi.findViewById(R.id.listCourseTeacher);
		TextView level = (TextView) vi.findViewById(R.id.listCourseLevel);

		name.setText(course.getName());
		teacher.setText(course.getTeacher());
		level.setText(String.valueOf(course.getLevel()));
		Presenter presenter = new GenericPresenter(course, vi);
		presenters.put(course, presenter);
		return presenter;
	}

	@ModelEventListener(events = {Model.ON_CHANGE})
	public void onChange() {
		this.notifyDataSetChanged();
	}
}
