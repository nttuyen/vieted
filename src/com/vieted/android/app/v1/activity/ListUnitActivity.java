package com.vieted.android.app.v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServicesHelpers;
import com.vieted.android.app.R;
import com.vieted.android.app.v1.VietEdState;
import com.vieted.android.app.v1.adapter.ListUnitAdapter;
import com.vieted.android.app.v1.dto.Course;
import com.vieted.android.app.v1.dto.Unit;
import com.vieted.android.app.v1.service.VietEdService;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class ListUnitActivity extends VietEdBaseActivity {
    private Service<Map<String, String>, Course> courseDetailService;

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setTextHeader("List Unit");
        this.setHomeBackAction();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if(url == null) {
            url = "__NOT_FOUND__";
        }

        this.courseDetailService = new VietEdService<Course>(url, Request.HTTP_GET, Course.class);
        this.courseDetailService.init(new HashMap<String, String>());
        ServicesHelpers.startService(this.courseDetailService, this, new Handler<Course>() {
            @Override
            public void handle(Course course) {
                VietEdState state = VietEdState.getInstance();
                state.setCurrentCourse(course);
                initbodyView();
            }
        });
    }

    public void initbodyView() {
        setLayoutBody(R.layout.activity_body_listcourse);
        TextView label1 = (TextView) findViewById(R.id.listLabel1);
        TextView label3 = (TextView) findViewById(R.id.listLabel3);
        label1.setText("Name");
        label3.setText("Scored");

        ListView listView = (ListView) findViewById(R.id.listCourseTotal);
        listView.setAdapter(new ListUnitAdapter(this, this.courseDetailService));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index,
                                    long l) {
                Unit unit = (Unit)adapter.getItemAtPosition(index);
                String url = unit.getUrl();
                Bundle extra = new Bundle();
                extra.putString("url", url);
                go(ListLessonActivity.class, extra);
            }
        });
    }
}
