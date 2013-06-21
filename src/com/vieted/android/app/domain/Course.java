package com.vieted.android.app.domain;

import java.util.List;

public class Course extends VietEdDomain {

    public static final byte COURSE_BEGINNER = 1;
    public static final byte COURSE_ADVANCED = 12;

    protected String name;
    protected float percentCompleted;
    protected float scored;
    protected String teacher;
    protected byte level;
    protected String url;
    
    
    
    
    

    public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public void setPercentCompleted(float percentCompleted) {
		this.percentCompleted = percentCompleted;
	}



	public void setScored(float scored) {
		this.scored = scored;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public float getPercentCompleted() {
		return percentCompleted;
	}



	public void setPercentCompleted(byte percentCompleted) {
		this.percentCompleted = percentCompleted;
	}



	public float getScored() {
		return scored;
	}



	public void setScored(byte scored) {
		this.scored = scored;
	}



	public String getTeacher() {
		return teacher;
	}



	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}



	public byte getLevel() {
		return level;
	}



	public void setLevel(byte level) {
		this.level = level;
	}



	public List<Unit> getUnits() {
		return units;
	}



	public void setUnits(List<Unit> units) {
		this.units = units;
	}



	public static byte getCourseBeginner() {
		return COURSE_BEGINNER;
	}



	public static byte getCourseAdvanced() {
		return COURSE_ADVANCED;
	}



	protected List<Unit> units;
}
