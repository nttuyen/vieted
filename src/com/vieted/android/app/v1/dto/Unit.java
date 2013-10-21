package com.vieted.android.app.v1.dto;

import com.nttuyen.android.base.json.Json;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class Unit implements Serializable {
    private String id;
    private String name;
    private int progress;
    private double stars;
    private String url;
    @Json(name = "lessons", isCollection = true, type = Lesson.class)
    private List<Lesson> lessons;

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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
