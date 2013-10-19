package com.vieted.android.app.v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServicesHelpers;
import com.vieted.android.app.R;
import com.vieted.android.app.v1.adapter.ListCourseAdapter;
import com.vieted.android.app.v1.dto.Course;
import com.vieted.android.app.v1.service.VietEdService;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class ListCourseActivity extends VietEdBaseActivity {
    private Service<Map<String, String>, Course> listCourseService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setTextHeader("List Course");
        this.setHomeBackAction();

        this.listCourseService = new VietEdService("/course", Request.HTTP_GET, Course.class);
        this.listCourseService.init(new HashMap<String,String>());
        ServicesHelpers.startService(this.listCourseService, this, new Handler<List<Course>>() {
            @Override
            public void handle(List<Course> courses) {
                initbodyView();
            }
        });
    }

    public void initbodyView(){
        setLayoutBody(R.layout.activity_body_listcourse);

        ListView listView = (ListView) findViewById(R.id.listCourseTotal);
        ListAdapter listAdapter = new ListCourseAdapter(this, this.listCourseService);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index,long l) {
                Course course = (Course)adapter.getItemAtPosition(index);
                String url = course.getUrl();
                Bundle extra = new Bundle();
                extra.putString("url", url);
                go(ListUnitActivity.class, extra);
            }
        });
    }
}
