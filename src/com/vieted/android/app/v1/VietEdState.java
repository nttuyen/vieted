package com.vieted.android.app.v1;

import com.vieted.android.app.v1.dto.*;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class VietEdState {
    private static VietEdState instance;
    private VietEdState() {

    }

    public static VietEdState getInstance() {
        if(instance == null) {
            synchronized (VietEdState.class) {
                //Because app alway run in multi-thread
                //So we need double check at here
                if(instance == null) {
                    instance = new VietEdState();
                }
            }
        }
        return instance;
    }

    private User user;
    public void setCurrentUser(User user) {
        this.user = user;
    }
    public User getCurrentUser() {
        return this.user;
    }

    private Course course;
    public void setCurrentCourse(Course course) {
        this.course = course;
    }
    public Course getCurrentCourse() {
        return this.course;
    }

    private Unit unit;
    public void setCurrentUnit(Unit unit) {
        this.unit = unit;
    }
    public Unit getCurrentUnit() {
        return this.unit;
    }

    private Lesson lesson;
    public void setCurrentLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    public Lesson getCurrentLesson() {
        return this.lesson;
    }

    private Exercise exercise;
    public void setCurrentExercise(Exercise exercise) {
        this.exercise = exercise;
    }
    public Exercise getCurrentExercise() {
        return this.exercise;
    }
}
