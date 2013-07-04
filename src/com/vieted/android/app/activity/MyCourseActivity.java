package com.vieted.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.androidteam.base.task.RestAsyncTask;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.LazyAdapter;
import com.vieted.android.app.task.GetCourseListTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyCourseActivity extends VietEdBaseActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTextHeader("My Courses");
        this.setHomeBackAction();
        this.mainTask = new GetCourseListTask();
        this.mainTask.setRestAsyncTaskListener(this);
        this.mainTask.execute();
    }

    public void initBodyView() {
        this.setLayoutBody(R.layout.activity_body_courses);
        ListView listView = (ListView)findViewById(R.id.listViewCourses);
        List<Map<String, String>> courses = new ArrayList<Map<String, String>>();
        for(int i = 0; i < 10; i++) {
            Map<String, String> course = new HashMap<String, String>();
            course.put("NAME", "Course No." + i);
            course.put("PERCENT_COMPLETED", i*10 + "%");
            course.put("SCORE", "****");
            courses.add(course);
        }
        ListAdapter adapter = new LazyAdapter(this, courses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyCourseActivity.this, LessonLectureActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        listView.invalidateViews();
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        if(task == this.mainTask) {
            this.initBodyView();
        }
    }
}
