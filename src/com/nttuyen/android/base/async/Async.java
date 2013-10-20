package com.nttuyen.android.base.async;

import android.os.AsyncTask;
import com.nttuyen.android.base.Callback;
import com.nttuyen.android.base.http.HTTP;
import com.nttuyen.android.base.http.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nttuyen266@gmail.com
 */
public class Async {
	public static final String OPTION_TASK_NAME = "staskName";
	public static final String OPTION_ON_PRE_EXECUTE = "onPreExecute";
	public static final String OPTION_ON_POST_EXECUTE = "onPostExecute";
	public static final String OPTION_ON_PROGRESS_UPDATE = "onProgressUpdate";
	public static final String OPTION_ON_CANCEL = "onCancel";

	/**
	 * options:
	 * OPTION_TASK_NAME taskName - default HTTP.NAME
	 * OPTION_ON_PRE_EXECUTE Callback
	 * OPTION_ON_POST_EXECUTE Callback
	 * OPTION_ON_PROGRESS_UPDATE Callback
	 * OPTION_ON_CANCEL Callback
	 *
	 * All Callback when execute will is: execute(taskName, ...)
	 * @param options
	 */
	public static void http(final Map<String, Object> options) {
		if(options == null) {
			throw new IllegalArgumentException("Option must not be null");
		}

		String task = HTTP.NAME;
		Callback prec = null;
		Callback postc = null;
		Callback progressc = null;
		Callback cancelc = null;

		if(options.containsKey(OPTION_TASK_NAME) && options.get(OPTION_TASK_NAME) instanceof String) {
			task = (String)options.get(OPTION_TASK_NAME);
			options.remove(OPTION_TASK_NAME);
		}
		if(options.containsKey(OPTION_ON_PRE_EXECUTE) && options.get(OPTION_ON_PRE_EXECUTE) instanceof Callback) {
			prec = (Callback)options.get(OPTION_ON_PRE_EXECUTE);
			options.remove(OPTION_ON_PRE_EXECUTE);
		}
		if(options.containsKey(OPTION_ON_POST_EXECUTE) && options.get(OPTION_ON_POST_EXECUTE) instanceof Callback) {
			postc = (Callback)options.get(OPTION_ON_POST_EXECUTE);
			options.remove(OPTION_ON_POST_EXECUTE);
		}
		if(options.containsKey(OPTION_ON_PROGRESS_UPDATE) && options.get(OPTION_ON_PROGRESS_UPDATE) instanceof Callback) {
			progressc = (Callback)options.get(OPTION_ON_PROGRESS_UPDATE);
			options.remove(OPTION_ON_PROGRESS_UPDATE);
		}
		if(options.containsKey(OPTION_ON_CANCEL) && options.get(OPTION_ON_CANCEL) instanceof Callback) {
			cancelc = (Callback)options.get(OPTION_ON_CANCEL);
			options.remove(OPTION_ON_CANCEL);
		}

		final String name = task;
		final Callback pre = prec;
		final Callback post = postc;
		final Callback progress = progressc;
		final Callback cancel = cancelc;

		AsyncTask async = new AsyncTask() {
			@Override
			protected Object doInBackground(Object... objects) {
				HTTP.execute(options);
				return null;
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				if(pre != null) {
					pre.execute(name);
				}
			}

			@Override
			protected void onPostExecute(Object o) {
				super.onPostExecute(o);
				if(post != null) {
					post.execute(name);
				}
			}

			@Override
			protected void onProgressUpdate(Object... values) {
				super.onProgressUpdate(values);
				if(progress != null) {
					progress.execute(name);
				}
			}

			@Override
			protected void onCancelled() {
				super.onCancelled();
				if(cancel != null) {
					cancel.execute(name);
				}
			}
		};
		async.execute();
	}
	public static void http(String url, Map<String, Object> options) {
		if(options == null) {
			options = new HashMap<String, Object>();
		}
		options.put(HTTP.OPTION_URL, url);
		http(options);
	}
}
