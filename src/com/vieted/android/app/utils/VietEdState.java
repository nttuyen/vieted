package com.vieted.android.app.utils;

import com.google.android.youtube.player.YouTubePlayer;
import com.vieted.android.app.domain.*;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/8/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class VietEdState {
    private static VietEdState currentState;
    public static VietEdState getInstance() {
        if(currentState == null) {
            currentState = new VietEdState();
        }
        return currentState;
    }

    private VietEdState() {
        //TODO: init repository
    }

    private Course currentCourse;
    private Unit currentUnit;
    private Lesson currentLesson;
    private Lecture currentLecture;
    private Quiz currentQuiz;

    private YouTubePlayer currentYoutubePlayer;

    public Quiz getCurrentQuiz() {
        return this.currentQuiz;
    }
    public void setCurrentQuiz(Quiz  q) {
        this.currentQuiz = q;
    }

    public YouTubePlayer getCurrentYoutubePlayer() {
        return this.currentYoutubePlayer;
    }
    public void setCurrentYoutubePlayer(YouTubePlayer player) {
        this.currentYoutubePlayer = player;
    }
}
