package com.vieted.android.app.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/6/13
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Question implements Serializable {
    private String questionText;
    private String audio;
    private String video;
    private String[] answers;
    private boolean[] results;
    private float[] scores;

    public Question() {
        this.questionText = "";
        this.audio = "";
        this.video = "";
        this.answers = new String[0];
        this.results = new boolean[0];
        this.scores = new float[0];
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public boolean[] getResults() {
        return results;
    }

    public void setResults(boolean[] results) {
        this.results = results;
    }

    public float[] getScores() {
        return scores;
    }

    public void setScores(float[] scores) {
        this.scores = scores;
    }
}
