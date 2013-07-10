package com.vieted.android.app.v1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.service.ServicesHelpers;
import com.nttuyen.android.base.service.VoidService;
import com.vieted.android.app.R;
import com.vieted.android.app.v1.adapter.ListExerciseAdapter;
import com.vieted.android.app.v1.adapter.ListLessonAdapter;
import com.vieted.android.app.v1.dto.Exercise;
import com.vieted.android.app.v1.dto.Lesson;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class LessonExerciseListActivity extends VietEdBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServicesHelpers.startService(new VoidService(), this, new Handler<Void>() {
            @Override
            public void handle(Void value) {
                initBody();
            }
        });
    }

    private void initBody() {
        setLayoutBody(R.layout.activity_body_listcourse);
        TextView label1 = (TextView) findViewById(R.id.listLabel1);
        TextView label3 = (TextView) findViewById(R.id.listLabel3);
        label1.setText("Name");
        label3.setText("Scored");

        ListView listView = (ListView) findViewById(R.id.listCourseTotal);
        listView.setAdapter(new ListExerciseAdapter(this, null));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index, long l) {
                Exercise exercise = (Exercise)adapter.getItemAtPosition(index);
                String url = exercise.getUrl();
                Bundle extra = new Bundle();
                extra.putString("url", url);
                go(LessonExerciseActivity.class, extra);
            }
        });
    }
}
