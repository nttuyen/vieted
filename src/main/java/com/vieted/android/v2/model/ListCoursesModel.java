package com.vieted.android.v2.model;

import android.os.AsyncTask;
import com.nttuyen.android.umon.core.mvc.Model;

/**
 * @author nttuyen266@gmail.com
 */
public class ListCoursesModel extends Model.Collection<CourseModel> {

	public ListCoursesModel() {

	}

	@Override
	public <K> K getId() {
		return null;
	}

	@Override
	public void fetch() {
		//Init some data
		//This is temporary block
		AsyncTask<Void, Void, Void> async = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				try {
					Thread.sleep(10000);
				} catch (Exception e){}
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				//TODO: init data
				Model.Collection<CourseModel> models = ListCoursesModel.this;
				for(int i = 1; i < 20; i++) {
					CourseModel course = new CourseModel();
					course.setId("id" + i);
					course.setName("Course name number " + i);
					course.setTeacher("Taught by teacher" + (i % 3));
					course.setLevel(i % 5);

					models.add(course);
				}

				trigger(ON_CHANGE, models);
				trigger(ON_PROCESS_COMPLETED, models, "void");
			}
		};

		this.trigger(ON_PROCESS_START, this, "void");
		async.execute(null);
	}

	@Override
	public void save() {

	}
}
