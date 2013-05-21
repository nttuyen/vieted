package com.vieted.android.app.task;

import com.androidteam.base.task.RestAsyncTask;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/21/13
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginTask extends RestAsyncTask {
    public LoginTask(String username, String password) {
        super();
        /*this.url = "http://vieted.com/user/login";
        this.addParam("api", "/user/login");
        this.addParam("_step", "account");
        this.addParam("_lname", "dev");
        this.addParam("_cl_token", "");
        this.addParam("_cl_uid", "");
        this.addParam("_cl_uhash", "");
        this.addParam("_cl_step", "account");
        this.addParam("_cl_rest", "1");
        this.addParam("_cl_ajax", "1");
        this.addParam("opponent_type", "cl_no_search");
        this.addParam("lname", username);
        this.addParam("mail", "");
        this.addParam("name", "");
        this.addParam("pass", password);
        this.addParam("login", "facebook");
        this.addParam("avatar", "");
        this.addParam("category", "vocabulary");
        this.addParam("_cl_submit", "Submit");*/
    }
}
