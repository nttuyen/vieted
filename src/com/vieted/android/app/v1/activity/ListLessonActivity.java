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
import com.vieted.android.app.v1.adapter.ListLessonAdapter;
import com.vieted.android.app.v1.dto.Lesson;
import com.vieted.android.app.v1.dto.Unit;
import com.vieted.android.app.v1.service.VietEdService;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListLessonActivity extends VietEdBaseActivity {
    private Service<Map<String, String>, Unit> unitDetailService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTextHeader("List Lesson");
        this.setHomeBackAction();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        this.unitDetailService = new VietEdService<Unit>(url, Request.HTTP_GET, Unit.class);
        this.unitDetailService.init(new HashMap<String, String>());
        ServicesHelpers.startService(this.unitDetailService, this, new Handler<Unit>() {
            @Override
            public void handle(Unit unit) {
                VietEdState state = VietEdState.getInstance();
                state.setCurrentUnit(unit);
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
        listView.setAdapter(new ListLessonAdapter(this, this.unitDetailService));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index, long l) {
                Lesson lesson = (Lesson)adapter.getItemAtPosition(index);
                String url = lesson.getUrl();
                Bundle extra = new Bundle();
                extra.putString("url", url);
                go(LessonLectureActivity.class, extra);
            }
        });
    }
}
