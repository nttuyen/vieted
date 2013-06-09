package com.vieted.android.app.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/8/13
 * Time: 10:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Lesson extends VietEdDomain {
    protected String name;
    protected byte scored;

    protected String teacher;

    protected List<Lecture> lectures;
    protected List<Quiz> quizs;
    protected List<Note> notes;

    public Lesson() {
        this.lectures = null;
        this.quizs = null;
        this.notes = null;
    }
}
