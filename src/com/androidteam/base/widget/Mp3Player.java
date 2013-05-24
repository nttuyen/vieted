package com.androidteam.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.vieted.android.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/24/13
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mp3Player extends RelativeLayout implements View.OnClickListener, MediaPlayer.OnCompletionListener{
    protected LayoutInflater mInflater;
    protected RelativeLayout mBarView;
    protected Button playerButton;
    protected ProgressBar playerTimeline;
    protected MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private String dataSource = null;
    protected Listener listener;

    public Mp3Player(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mBarView = (RelativeLayout) mInflater.inflate(R.layout.mp3_player, null);
        addView(mBarView);

        this.playerTimeline = (ProgressBar) mBarView.findViewById(R.id.playerTimeline);
        this.playerButton = (Button)mBarView.findViewById(R.id.playerButton);
        this.playerButton.setOnClickListener(this);

        this.mediaPlayer = null;
    }

    public void setDataSource(String datasource) {
        this.dataSource = datasource;
    }

    public void pause() {
        if(this.mediaPlayer != null) {
            this.mediaPlayer.pause();
            this.playerButton.setText("Play");
            if(this.listener != null) {
                this.listener.onPaused(this);
            }
        }
    }

    public void destroy() {
        if(this.mediaPlayer != null) {
            try {
                this.mediaPlayer.release();
                this.mediaPlayer = null;
            } catch (Exception e) {}
        }
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(playerButton.getText().equals("Pause")) {
            if(this.listener != null) {
                this.listener.onPaused(this);
            }
            mediaPlayer.pause();
            playerButton.setText("Play");
            return;
        }

        try {
            if(this.listener != null) {
                this.listener.onPlayStart(this);
            }
            if(this.mediaPlayer == null) {
                this.mediaPlayer = new MediaPlayer();
                this.mediaPlayer.setDataSource(this.dataSource);
                this.mediaPlayer.prepare();
                this.playerTimeline.setMax(this.mediaPlayer.getDuration());
            }
            mediaPlayer.start();
            updateProgressBar();
            playerButton.setText("Pause");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if(this.listener != null) {
            this.listener.onPlayCompleted(this);
        }
        playerButton.setText("Play");
    }

    private Runnable onEverySecond=new Runnable() {
        public void run() {
            if (mediaPlayer!=null) {
                playerTimeline.setProgress(mediaPlayer.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);
        }
    };
    private void updateProgressBar() {
        handler.postDelayed(onEverySecond, 1000);
    }

    @Override
    protected void finalize() throws Throwable {
        this.destroy();
        super.finalize();
    }

    public interface Listener {
        public void onPlayStart(Mp3Player player);
        public void onPlayCompleted(Mp3Player player);
        public void onPaused(Mp3Player player);
    }
}
