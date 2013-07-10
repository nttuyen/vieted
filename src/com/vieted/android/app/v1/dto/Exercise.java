package com.vieted.android.app.v1.dto;

import com.nttuyen.android.base.converter.Json;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Exercise implements Serializable {
    private String id;
    private String name;
    private String content;
    private String lesson;
    private String type;
    private String skill;
    private String mp;
    private long duration;
    @Json(name = "timing_type")
    private int timmingType;
    private int oi;
    private String qdm;
    private double stars;
    private String url;
    @Json(name = "questions", isCollection = true, type = Question.class)
    private List<Question> questions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getTimmingType() {
        return timmingType;
    }

    public void setTimmingType(int timmingType) {
        this.timmingType = timmingType;
    }

    public int getOi() {
        return oi;
    }

    public void setOi(int oi) {
        this.oi = oi;
    }

    public String getQdm() {
        return qdm;
    }

    public void setQdm(String qdm) {
        this.qdm = qdm;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
