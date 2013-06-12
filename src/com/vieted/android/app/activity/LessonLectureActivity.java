package com.vieted.android.app.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.androidteam.base.task.RestAsyncTask;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.vieted.android.app.R;
import com.vieted.android.app.task.VoidTask;
import com.vieted.android.app.utils.Const;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/23/13
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class LessonLectureActivity extends VietEdWithYoutubeBaseActivity {
    private YouTubePlayer player;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mainTask = new VoidTask();
        this.mainTask.setRestAsyncTaskListener(this);
        this.mainTask.execute();

        this.actionBar.setProgressBarVisibility(View.VISIBLE);
    }

    protected void initControl() {
        this.setLayoutBody(R.layout.activity_body_lesson_lecture);
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.lessonLectureYoutubeView);
        youTubeView.initialize(Const.GOOGLE_API_ANDROID_DEVELOPER_KEY, this);
        
        Button button = (Button)this.findViewById(R.id.lessonButtonExercise);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LessonLectureActivity.this, LessonExerciseFragmentSupportActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			}
		});
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(this.player != null) {
            if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                this.player.setFullscreen(true);
            }
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        this.initControl();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        LessonLectureActivity.this.player = player;
        if (!wasRestored) {
            player.cueVideo("wKJ9KzGQq0w");
            player.setFullscreen(true);
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) this.findViewById(R.id.lessonLectureYoutubeView);
    }
}
