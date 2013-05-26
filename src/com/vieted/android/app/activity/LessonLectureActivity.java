package com.vieted.android.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.androidteam.base.task.RestAsyncTask;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.vieted.android.app.R;
import com.vieted.android.app.task.VoidTask;
import com.vieted.android.app.utils.DeveloperKey;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/23/13
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class LessonLectureActivity extends VietEdWithYoutubeBaseActivity {
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
        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
        
        Button button = (Button)this.findViewById(R.id.lessonButtonExercise);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LessonLectureActivity.this, LessonExerciseActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
			}
		});
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        this.initControl();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo("wKJ9KzGQq0w");
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) this.findViewById(R.id.lessonLectureYoutubeView);
    }
}
