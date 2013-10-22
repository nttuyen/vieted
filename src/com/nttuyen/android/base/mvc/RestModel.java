package com.nttuyen.android.base.mvc;

import com.nttuyen.android.base.Callback;
import com.nttuyen.android.base.async.Async;
import com.nttuyen.android.base.json.JsonConvertHelper;
import com.nttuyen.android.base.http.HTTP;
import com.nttuyen.android.base.http.JsonResponse;
import com.nttuyen.android.base.http.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class RestModel extends Model {
	public static final String PROCESS_HTTP_REQUEST = "http_request";

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
			trigger(ON_PROCESS_COMPLETED, this, PROCESS_HTTP_REQUEST);
		}

		response = new JsonResponse();

		Map<String, Object> options = new HashMap<String, Object>();
		options.put(Async.OPTION_TASK_NAME, PROCESS_HTTP_REQUEST);
		options.put(Async.OPTION_ON_PRE_EXECUTE, preAsync);
		//options.put(Async.OPTION_ON_POST_EXECUTE, postAsync);
		options.put(HTTP.OPTION_SUCCESS, onHttpSuccess);
		options.put(HTTP.OPTION_ERROR, onHttpFailure);

		Async.http(url, options);
	}

	protected void fromJson(JSONObject json) {
		try {
			JsonConvertHelper.inject(json, this);
			trigger(ON_PROCESS_COMPLETED, this, PROCESS_HTTP_REQUEST);
		} catch (Exception ex) {
			trigger(ON_PROCESS_ERROR, RestModel.this, PROCESS_HTTP_REQUEST, 409, "Conflict", "Response message is not as expected");
		}
	}

	//Pre async execute
	final Callback preAsync = new Callback() {
		@Override
		public void execute(Object... params) {
			String taskName = (String)params[0];
			trigger(ON_PROCESS_START, RestModel.this, taskName);
		}
	};
	//Post async execute
	final Callback postAsync = null;

	//HTTP success
	final Callback onHttpSuccess = new Callback() {
		@Override
		public void execute(Object... params) {
			fromJson(response.getResult());
		}
	};

	//Http failure
	final Callback onHttpFailure = new Callback() {
		@Override
		public void execute(Object... params) {
			int code = (Integer)params[0];
			String status = (String)params[1];
			String message = (String)params[2];
			Response response = (Response)params[3];

			trigger(ON_PROCESS_ERROR, RestModel.this, PROCESS_HTTP_REQUEST, code, status, message);
		}
	};
}
