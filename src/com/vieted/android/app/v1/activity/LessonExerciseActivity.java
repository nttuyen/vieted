package com.vieted.android.app.v1.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.androidteam.base.task.RestAsyncTask;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.nttuyen.android.base.handler.Handler;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.service.Service;
import com.nttuyen.android.base.service.ServicesHelpers;
import com.vieted.android.app.R;
import com.vieted.android.app.adapter.QuestionPagerAdapter;
import com.vieted.android.app.domain.Question;
import com.vieted.android.app.domain.Quiz;
import com.vieted.android.app.task.VoidTask;
import com.vieted.android.app.utils.Const;
import com.vieted.android.app.v1.VietEdState;
import com.vieted.android.app.v1.dto.Exercise;
import com.vieted.android.app.v1.dto.ExerciseRestResult;
import com.vieted.android.app.v1.service.VietEdService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class LessonExerciseActivity extends VietEdFragmentSupportBaseActivity {
    private ViewPager questionPager;
    private YouTubePlayer player = null;
    private YouTubePlayerSupportFragment youtubeFragment;
    private QuestionPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");

        Service<Map<String, String>, ExerciseRestResult> service = new VietEdService<ExerciseRestResult>(url, Request.HTTP_GET, ExerciseRestResult.class);
        service.init(new HashMap<String, String>());
        ServicesHelpers.startService(service, this, new Handler<ExerciseRestResult>() {
            @Override
            public void handle(ExerciseRestResult result) {
                Exercise exercise = result.getExercise();
                exercise.setUrl(url);

                VietEdState state = VietEdState.getInstance();
                state.setCurrentExercise(exercise);

                initBodyView();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(this.player != null) {
            if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                this.player.setFullscreen(true);
                player.setShowFullscreenButton(false);
            } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                this.player.setFullscreen(false);
                player.setShowFullscreenButton(true);
            }
        }
        super.onConfigurationChanged(newConfig);
    }

    private void initBodyView() {
        this.setLayoutBody(R.layout.body_lesson_exercise);

        //Init youtbeFragment :)
        this.youtubeFragment = (YouTubePlayerSupportFragment)this.getSupportFragmentManager().findFragmentById(R.id.youtubePlayerFragment);
        this.youtubeFragment.setRetainInstance(true);
        this.youtubeFragment.initialize(Const.GOOGLE_API_ANDROID_DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                LessonExerciseActivity.this.player = youTubePlayer;
                if(!b) {
                    initQuizYoutubeVideo();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {}
        });
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(this.youtubeFragment);
        fragmentTransaction.commit();

        //Process question pager
        this.questionPager = (ViewPager)this.findViewById(R.id.questionViewPager);
        this.adapter = new QuestionPagerAdapter(this.getSupportFragmentManager());
        this.questionPager.setAdapter(this.adapter);
    }

    private void initQuizYoutubeVideo() {
        if(this.player == null) {
            return;
        }
        //TODO: play video if needed
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(adapter.hasMoreQuestion()) {
                adapter.allowNext();
            }
        }
    };
}
