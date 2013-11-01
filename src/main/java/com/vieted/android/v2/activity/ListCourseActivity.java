package com.vieted.android.v2.activity;

import android.os.Bundle;
import android.widget.ListView;
import com.nttuyen.android.umon.core.mvc.Model;
import com.nttuyen.android.umon.core.mvc.ModelEventListener;
import com.vieted.android.v2.R;
import com.vieted.android.v2.adapter.ListCourseAdapter;
import com.vieted.android.v2.model.ListCoursesModel;

/**
 * @author nttuyen266@gmail.com
 */
public class ListCourseActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setBodyView(R.layout.body_courses);
		this.setModel(new ListCoursesModel());

		ListView courses = (ListView)this.findViewById(R.id.listCourseTotal);
		ListCourseAdapter adapter = new ListCourseAdapter(this, (ListCoursesModel)this.getModel());
		courses.setAdapter(adapter);
	}

	@Override
	protected void onStart() {
		System.out.println("ONStart");
		super.onStart();
	}
}
