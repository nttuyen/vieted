package com.vieted.android.app.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/8/13
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Quiz extends VietEdDomain {
    protected String name;
    protected String description;
    protected String image;
    protected String video;
    protected String audio;
    protected byte scored;

    protected List<Question> questions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public byte getScored() {
        return scored;
    }

    public void setScored(byte scored) {
        this.scored = scored;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
