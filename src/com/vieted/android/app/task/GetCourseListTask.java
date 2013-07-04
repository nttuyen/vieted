package com.vieted.android.app.task;

import com.androidteam.base.task.RestAsyncTask;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class GetCourseListTask extends RestAsyncTask{
    public GetCourseListTask() {
        this.url = "http://dev.vieted.com/course";
        this.addParam("api", "/course");
        this.addParam("_step", "/course");
        this.addParam("_lname", "dev");
        this.addParam("_cl_token", "");
        this.addParam("_cl_uid", "");
        this.addParam("_cl_uhash", "");
        this.addParam("_cl_step", "account");
        this.addParam("_cl_rest", "1");
        this.addParam("_cl_ajax", "1");
        this.addParam("opponent_type", "cl_no_search");
        this.addParam("lname", "");
        this.addParam("mail", "");
        this.addParam("name", "");
        this.addParam("pass", "");
        this.addParam("login", "facebook");
        this.addParam("avatar", "");
        this.addParam("category", "vocabulary");
        this.addParam("_cl_submit", "Submit");
    }
}
