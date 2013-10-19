package com.vieted.android.app.task;

import com.androidteam.base.task.RestAsyncTask;

@Deprecated
public class GetUnitTask extends RestAsyncTask{
	public GetUnitTask(String url, String api){
		 this.url = "http://dev.vieted.com/"+url;
	        this.addParam("api", api);
	        this.addParam("_step", "account");
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
