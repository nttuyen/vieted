package com.vieted.android.app.utils;

import android.media.MediaPlayer;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/23/13
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class MediaPlayerUtils {
    private static MediaPlayer mediaPlayer;
    public static MediaPlayer getMediaPlayer() {
        if(mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setScreenOnWhilePlaying(true);
        }
        return mediaPlayer;
    }
}
