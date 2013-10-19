package com.nttuyen.android.base.async;

import android.os.AsyncTask;
import com.nttuyen.android.base.Callback;
import com.nttuyen.android.base.http.HTTP;
import com.nttuyen.android.base.http.Response;

import java.util.Map;

/**
 * @author nttuyen266@gmail.com
 */
public class Async {
	public static void httpGet(final String url, final Map<String, String> data, final Response response, final Callback preExecute, final Callback postExecute, final Callback onHttpSuccess, final Callback onHttpFailure) {
		AsyncTask async = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				HTTP.get(url, data, response, onHttpSuccess, onHttpFailure);
				return null;
			}

			@Override
			protected void onPreExecute() {
				if (preExecute != null) {
					preExecute.execute();
				}
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				if (postExecute != null) {
					postExecute.execute();
				}
				super.onPostExecute(aVoid);
			}
		};
		async.execute();
	}

	public static <D> void httpPost(final String url, final D data, final Response response, final Callback preExecute, final Callback postExecute, final Callback onHttpSuccess, final Callback onHttpFailure) {
		AsyncTask async = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... voids) {
				HTTP.post(url, data, response, onHttpSuccess, onHttpFailure);
				return null;
			}

			@Override
			protected void onPreExecute() {
				if (preExecute != null) {
					preExecute.execute();
				}
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				if (postExecute != null) {
					postExecute.execute();
				}
				super.onPostExecute(aVoid);
			}
		};
		async.execute();
	}
}
