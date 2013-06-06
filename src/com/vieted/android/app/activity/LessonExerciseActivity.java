package com.vieted.android.app.activity;

import android.os.Bundle;
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
public class LessonExerciseActivity extends VietEdWithYoutubeBaseActivity implements Mp3Player.Listener{
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

        this.players.get(0).setDataSource("http://stream2.s2.mp3.zdn.vn/fsfsdfdsfdserwrwq3/d6ba852aefd52df6e4e009765698da63/51af5b14/2013/04/23/e/d/ed2e0aa4330be96bbb4fb2b57f722180.mp3", false);
        this.players.get(1).setDataSource("http://stream2.mp3.zdn.vn/fsfsdfdsfdserwrwq3/0973fb1b1bdd899fee14bd6b91794779/51a1ff64/2011/02/24/3/8/3866a8205b9ba076229c41a0f931ea6c.mp3");
        this.players.get(2).setDataSource("http://stream2.mp3.zdn.vn/fsfsdfdsfdserwrwq3/2191a13cf42aad410cbe62ff5a4ba74f/51a1ff78/2010/12/22/4/a/4a11d9bbc5ad8e35019d3903b4280dfb.mp3");

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
