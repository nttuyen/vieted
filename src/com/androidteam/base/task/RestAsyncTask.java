package com.androidteam.base.task;

import android.os.AsyncTask;
import android.util.Log;
import com.androidteam.base.listener.RestAsyncTaskListener;
import com.androidteam.base.utils.JsonUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nttuyen266@gmail.com
 */
@Deprecated
public abstract class RestAsyncTask extends AsyncTask<Void, Void, JSONObject> {
    protected static final HttpClient httpClient;

    protected String url;
    protected Map<String, String> params;
    protected RestAsyncTaskListener listener;
    protected String method;

    protected JSONObject json;
    protected Exception lastException;

    static {
        httpClient = new DefaultHttpClient();
    }
    public RestAsyncTask() {
        this.url = null;
        this.params = new HashMap<String, String>();
        this.listener = null;
        this.method = "GET";
    }

    public void setRestAsyncTaskListener(RestAsyncTaskListener listener) {
        this.listener = listener;
    }
    public void setURL(String url) {
        this.url = url;
    }
    public void addParam(String name, String value) {
        this.params.put(name, value);
    }
    public void removeParam(String name) {
        this.params.remove(name);
    }

    public JSONObject getLastResult() {
        return this.json;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        if(this.url == null || "".equals(this.url) || !(this.url.startsWith("http://") || this.url.startsWith("https://"))) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            this.json = null;
            this.lastException = null;
            return this.json;
        }
        try {
            String jsonString = this.executeHttp();
            this.json = new JSONObject(jsonString);
            this.lastException = null;
        } catch (Exception e) {
            this.json = null;
            this.lastException = e;
        }
        return this.json;
    }

    @Override
    protected void onPreExecute() {
        if(this.listener != null) {
            this.listener.onTaskStart();
        }
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        if(this.listener == null) return;

        if(this.lastException != null) {
            this.listener.onTaskFailure(this, this.lastException);
            this.lastException = null;
            return;
        }

        if (json != null) {
            if (JsonUtils.getReturnStatus(json)) {
                this.listener.onTaskSuccess(this);
                return;
            } else {
                try {
                    String message = json.getString("err");
                    this.listener.onTaskFailure(this, message);
                    return;
                } catch (JSONException e) {
                    this.listener.onTaskFailure(this, "Data format error");
                    return;
                }
            }
        }
        this.listener.onTaskSuccess(this);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        if(this.listener != null) {
            this.listener.onTaskProgress(this);
        }
    }

    protected String executeHttp() throws Exception {
        HttpUriRequest request = this.createRequest();
        HttpResponse response = httpClient.execute(request);
        BufferedReader in = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

        StringBuilder builder = new StringBuilder();
        String output = null;
        while ((output = in.readLine()) != null) {
            builder.append(output);
        }

        Log.d("DEBUG", builder.toString());
        return builder.toString();
    }

    protected HttpUriRequest createRequest() throws Exception {
        HttpUriRequest request = "GET".equalsIgnoreCase(this.method) ? this.createHttpGet() : this.createHttpPost();
        return request;
    }

    protected HttpGet createHttpGet() throws Exception {
        StringBuilder sb = new StringBuilder(this.url);
        String paramString = URLEncodedUtils.format(this.getParams(), "utf-8");
        if(url.contains("?")) {
            if(url.endsWith("?")) {
                sb.append(paramString);
            } else {
                sb.append("&").append(paramString);
            }
        } else {
            sb.append("?").append(paramString);
        }

        return new HttpGet(sb.toString());
    }
    protected HttpPost createHttpPost() throws Exception {
        HttpPost post = new HttpPost(this.url);
        post.setEntity(new UrlEncodedFormEntity(this.getParams(), HTTP.UTF_8));
        return post;
    }
    protected List<NameValuePair> getParams() {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : this.params.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return pairs;
    }

}
