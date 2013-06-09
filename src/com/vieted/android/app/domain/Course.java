package com.vieted.android.app.domain;

import java.util.List;

public class Course extends VietEdDomain {

    public static final byte COURSE_BEGINNER = 1;
    public static final byte COURSE_ADVANCED = 12;

    protected String name;
    protected byte percentCompleted;
    protected byte scored;
    protected String teacher;
    protected byte level;

    protected List<Unit> units;
}
