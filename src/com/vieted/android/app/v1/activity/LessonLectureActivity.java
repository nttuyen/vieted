package com.vieted.android.app.v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServicesHelpers;
import com.vieted.android.app.R;
import com.vieted.android.app.Const;
import com.vieted.android.app.v1.VietEdState;
import com.vieted.android.app.v1.dto.Lesson;
import com.vieted.android.app.v1.dto.Video;
import com.vieted.android.app.v1.service.VietEdService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class LessonLectureActivity extends VietEdFragmentSupportBaseActivity {
    private YouTubePlayer player;

    private Service<Map<String, String>, Lesson> lessonDetailService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        this.lessonDetailService = new VietEdService<Lesson>(url, Request.HTTP_GET, Lesson.class);
        this.lessonDetailService.init(new HashMap<String, String>());
        ServicesHelpers.startService(this.lessonDetailService, this, new Handler<Lesson>() {
            @Override
            public void handle(Lesson lesson) {
                VietEdState state = VietEdState.getInstance();
                state.setCurrentLesson(lesson);
                initBodyView();
            }
        });
    }

    private void initBodyView() {
        this.setLayoutBody(R.layout.body_lesson_lecture);
        YouTubePlayerSupportFragment youtubeFragment = (YouTubePlayerSupportFragment)this.getSupportFragmentManager().findFragmentById(R.id.youtubePlayerFragment);
        youtubeFragment.initialize(Const.GOOGLE_API_ANDROID_DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                LessonLectureActivity.this.player = youTubePlayer;
                VietEdState state = VietEdState.getInstance();
                Lesson lesson = state.getCurrentLesson();
                Video video = lesson.getVideo();
                if(video != null && video.getId() != null && !video.getId().isEmpty()) {
                    LessonLectureActivity.this.player.cueVideo(video.getId());
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        Button exerciseButton = (Button)this.findViewById(R.id.lessonButtonExercise);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go(LessonExerciseListActivity.class);
            }
        });
    }
}
