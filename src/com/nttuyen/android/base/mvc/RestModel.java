package com.nttuyen.android.base.mvc;

import com.nttuyen.android.base.Callback;
import com.nttuyen.android.base.async.Async;
import com.nttuyen.android.base.converter.JsonConvertHelper;
import com.nttuyen.android.base.http.HTTP;
import com.nttuyen.android.base.http.JsonResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class RestModel extends Model {

	protected JsonResponse response = null;
	/**
	 * URL for get data
	 * @return
	 */
	public abstract String url();

	@Override
	public void fetch() {
		String url = this.url();

		if(url == null) {
			trigger(Events.ON_READY, this);
		}

		response = new JsonResponse();
		Async.httpGet(url, null, response, preAsync, postAsync, onHttpSuccess, onHttpFailure);
	}

	//Pre async execute
	final Callback preAsync = new Callback() {
		@Override
		public void execute(Object... params) {
			trigger(Events.ON_START_LOADING, RestModel.this);
		}
	};
	//Post async execute
	final Callback postAsync = null;

	//HTTP success
	final Callback onHttpSuccess = new Callback() {
		@Override
		public void execute(Object... params) {
			//TODO: need to improve because
			if(response == null) {
				trigger(Events.ON_HTTP_ERROR, RestModel.this, HTTP.ERROR_RESPONSE, 408, "empty response");
				return;
			}
				JSONObject json = response.getResult();
			try {
				JsonConvertHelper.fromJson(json, RestModel.this);
				trigger(Events.ON_READY, RestModel.this);
			} catch (JSONException ex) {
				trigger(Events.ON_HTTP_ERROR, RestModel.this, HTTP.ERROR_EXCEPTION, ex, "format error");
				return;
			}
		}
	};

	//Http failure
	final Callback onHttpFailure = new Callback() {
		@Override
		public void execute(Object... params) {
			//param[0] = this model
			//param[1] = ERROR_EXCEPTION | ERROR_RESPONSE
			//param[2] = exception if ERROR_EXCEPTION
			//param[2] = response code if ERROR_RESPONSE
			//param[.] = other param if exists
			trigger(Events.ON_HTTP_ERROR, RestModel.this, params);
		}
	};
}
