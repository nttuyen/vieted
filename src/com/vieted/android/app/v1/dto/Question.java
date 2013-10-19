package com.vieted.android.app.v1.dto;

import com.nttuyen.android.base.converter.Json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class Question implements Serializable {
    private String id;
    @Json(name = "content_display")
    private String content;
    @Json(name = "correct_answer")
    private int correctAnswer;
    @Json(name = "mc_answers", isCollection = true, type = String.class)
    private List<String> answers;
    private String skill;
    private int type;
    @Json(name = "hints", isCollection = true, type = String.class)
    private List<String> hints;

    private boolean completed = false;
    protected List<Boolean> results = new ArrayList<Boolean>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getHints() {
        return hints;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Boolean> getResults() {
        return results;
    }

    public void setResults(List<Boolean> results) {
        this.results = results;
    }
}
