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
    protected float scored;
    protected int progress;

    public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	protected String teacher;

    protected List<Lecture> lectures;
    protected List<Quiz> quizs;
    protected List<Note> notes;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getScored() {
		return scored;
	}

	public void setScored(float scored) {
		this.scored = scored;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public List<Quiz> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<Quiz> quizs) {
		this.quizs = quizs;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Lesson() {
        this.lectures = null;
        this.quizs = null;
        this.notes = null;
    }
}
