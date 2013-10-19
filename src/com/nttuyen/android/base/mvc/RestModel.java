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
		Async.httpGet(url, null, response, preAsync, postAsync, onHttpSuccess, onHttpFailure);
	}

	protected void fromJson(JSONObject json) {
		try {
			JsonConvertHelper.fromJson(json, this);
			trigger(ON_PROCESS_COMPLETED, this, PROCESS_HTTP_REQUEST);
		} catch (JSONException ex) {
			trigger(ON_PROCESS_ERROR, RestModel.this, PROCESS_HTTP_REQUEST, HTTP.ERROR_EXCEPTION, "format error", ex);
			return;
		}
	}

	//Pre async execute
	final Callback preAsync = new Callback() {
		@Override
		public void execute(Object... params) {
			trigger(ON_PROCESS_START, RestModel.this, PROCESS_HTTP_REQUEST);
		}
	};
	//Post async execute
	final Callback postAsync = null;

	//HTTP success
	final Callback onHttpSuccess = new Callback() {
		@Override
		public void execute(Object... params) {
			if(response == null) {
				trigger(ON_PROCESS_ERROR, RestModel.this, HTTP.ERROR_RESPONSE, 408, "empty response");
				return;
			}
			fromJson(response.getResult());
		}
	};

	//Http failure
	final Callback onHttpFailure = new Callback() {
		@Override
		public void execute(Object... params) {
			String errorType = (String)params[0];

			int trimArray = 1;
			if(errorType == HTTP.ERROR_EXCEPTION) {
				//RuntimeException exception = (RuntimeException)params[1];
				trimArray = 2;
			}

			Object[] newParams;
			int n = params.length - trimArray;
			if(n > 0) {
				newParams = new Object[n];
				System.arraycopy(params, trimArray, newParams, 0, n);
			} else {
				newParams = new Object[0];
			}
			trigger(ON_PROCESS_ERROR, RestModel.this, PROCESS_HTTP_REQUEST, errorType, "error on http process", newParams);
		}
	};
}
