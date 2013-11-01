package com.vieted.android.v2.model;

import com.nttuyen.android.umon.core.mvc.Model;

/**
 * @author nttuyen266@gmail.com
 */
public class CourseModel extends Model {
	private String id;
	private String name;
	private String teacher;
	private int level;

	public CourseModel() {}

	@Override
	public <K> K getId() {
		return (K)id;
	}

	@Override
	public void fetch() {
	}

	@Override
	public void save() {
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}
