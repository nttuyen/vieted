package com.vieted.android.app.task;

import com.androidteam.base.task.RestAsyncTask;

@Deprecated
public class RegisterTask extends RestAsyncTask {
	public RegisterTask(String pass,String mail, String name){
		super();
		this.url = "http://dev.vieted.com/user/register";
        this.addParam("api", "user/register");
        this.addParam("_step", "account");
        this.addParam("_lname", "dev");
        this.addParam("_cl_token", "");
        this.addParam("_cl_uid", "");
        this.addParam("_cl_uhash", "");
        this.addParam("_cl_step", "account");
        this.addParam("_cl_rest", "1");
        this.addParam("_cl_ajax", "1");
        this.addParam("opponent_type", "cl_no_search");
        this.addParam("lname","dev");
        this.addParam("mail",mail);
        this.addParam("name", name);
        this.addParam("pass", pass);
        this.addParam("login", "facebook");
        this.addParam("avatar", "");
        this.addParam("category", "vocabulary");
        this.addParam("_cl_submit", "Submit");

		
		
	}

}
