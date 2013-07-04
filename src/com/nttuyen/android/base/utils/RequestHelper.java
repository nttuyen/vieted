package com.nttuyen.android.base.utils;

import com.nttuyen.android.base.request.Request;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/28/13
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestHelper {
    public static String execute(HttpClient client, Request request) throws IOException {
        HttpUriRequest uriRequest = RequestHelper.createRequest(request);
        HttpResponse response = client.execute(uriRequest);
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder builder = new StringBuilder();
        String output = null;
        while ((output = in.readLine()) != null) {
            builder.append(output);
        }
        in.close();
        return builder.toString();
    }

    protected static HttpUriRequest createRequest(Request req) throws UnsupportedEncodingException {
        HttpUriRequest request = req.getMethod() == Request.HTTP_GET ? RequestHelper.createHttpGet(req) : RequestHelper.createHttpPost(req);
        return request;
    }

    protected static HttpGet createHttpGet(Request req) {
        String url = req.getURL();
        StringBuilder sb = new StringBuilder(req.getURL());
        String paramString = URLEncodedUtils.format(RequestHelper.getParams(req), "utf-8");
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

    protected static HttpPost createHttpPost(Request req) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(req.getURL());
        post.setEntity(new UrlEncodedFormEntity(RequestHelper.getParams(req), HTTP.UTF_8));
        return post;
    }
    protected static List<NameValuePair> getParams(Request req) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : req.getParameters().entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return pairs;
    }
}
