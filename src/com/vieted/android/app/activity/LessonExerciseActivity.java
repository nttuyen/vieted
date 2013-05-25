package com.vieted.android.app.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.androidteam.base.task.RestAsyncTask;
import com.androidteam.base.widget.Mp3Player;
import com.google.android.youtube.player.YouTubePlayer;
import com.vieted.android.app.R;
import com.vieted.android.app.task.VoidTask;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/24/13
 * Time: 6:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class LessonExerciseActivity extends AbstractLessonActivity implements Mp3Player.Listener{
    private List<Mp3Player> players;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.players = new LinkedList<Mp3Player>();

        this.mainTask = new VoidTask();
        this.mainTask.setRestAsyncTaskListener(this);
        this.mainTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(Mp3Player player : this.players) {
            player.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for(Mp3Player player : this.players) {
            player.destroy();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPlayStart(Mp3Player player) {
        for(Mp3Player p : this.players) {
            if(p != player) {
                p.pause();
            }
        }
    }

    @Override
    public void onPlayCompleted(Mp3Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPaused(Mp3Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void initBodyView() {
        this.setLayoutBody(R.layout.activity_body_lesson_exercise);
        this.players.add((Mp3Player)this.findViewById(R.id.mp3Player1));
        this.players.add((Mp3Player)this.findViewById(R.id.mp3Player2));
        this.players.add((Mp3Player)this.findViewById(R.id.mp3Player3));

        this.players.get(0).setDataSource("http://stream.org.mp3.zdn.vn/fsfsdfdsfdserwrwq3/f22234ddccfa54ad43b9fa5838e990c1/51a08c53/0/00/0009f615a07cc86154fc8dc0e64e387e.mp3");
        this.players.get(1).setDataSource("http://stream2.org.mp3.zdn.vn/fsfsdfdsfdserwrwq3/c0d2fdcabcd56c8744c96ad921c66d79/519f54c5/2013/05/15/2/5/25e96c111d36dbefea68a786876708d7.mp3");
        this.players.get(2).setDataSource("http://stream2.mp3.zdn.vn/fsfsdfdsfdserwrwq3/a0d20c02ff47d498457951e22c1d9f01/519f731f/2013/05/15/a/4/a47fb0d027a756c57467c242de0a4449.mp3");

        for(Mp3Player player : this.players) {
            player.setListener(this);
        }
    }

    @Override
    protected void handleGetSuccess(RestAsyncTask task) {
        this.initBodyView();
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
