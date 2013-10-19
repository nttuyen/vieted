package com.vieted.android.app.v1.service;

import com.nttuyen.android.base.request.GenericRequest;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.service.AsyncRestWithCacheService;
import com.nttuyen.android.base.service.ServiceException;
import com.vieted.android.app.Const;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 8:13 AM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class VietEdService<Result> extends AsyncRestWithCacheService<Map<String, String>, Result> {
    protected final int method;
    protected final String path;
    public VietEdService(String path, int method, Class<?> type) {
        super(type);
        this.path = path;
        this.method = method;
    }

    @Override
    protected Result fetchFromCache(Map<String, String> stringStringMap) throws ServiceException {
        return null;
    }

    @Override
    protected boolean storeToCache(Result result) throws ServiceException {
        return false;
    }

    @Override
    protected Request createRequest(Map<String, String> params) throws ServiceException {
        Request request = new GenericRequest(method) {
            @Override
            public String getURL() {
                if(VietEdService.this.path == null || VietEdService.this.path.isEmpty()) {
                    return Const.VIETED_API_URL_BASE;
                }
                return (VietEdService.this.path.charAt(0) == '/' ? Const.VIETED_API_URL_BASE : Const.VIETED_API_URL_BASE + "/") + VietEdService.this.path;
            }
        };

        //Put default param
        if(!params.containsKey("api")) {
            //TODO: need re-check this
            params.put("api", VietEdService.this.path);
        }
        if(!params.containsKey("_cl_ajax")) {
            params.put("_cl_ajax", "1");
        }
        if(!params.containsKey("_cl_submit")) {
            params.put("_cl_submit", "1");
        }
        if(!params.containsKey("_cl_token")) {
            //TODO: process TOKEN???
            params.put("_cl_token", "");
        }
        if(!params.containsKey("_cl_uhash")) {
            //TODO: process uHash???
            params.put("_cl_uhash", "");
        }
        if(!params.containsKey("_cl_uid")) {
            //TODO: prcoess UID
            params.put("_cl_uid", "");
        }
        if(!params.containsKey("_cl_rest")) {
            params.put("_cl_rest", "1");
        }

        request.setParameters(params);
        return request;
    }

    @Override
    protected boolean checkRestSuccess(JSONObject json) throws JSONException {
        Boolean code = json.getBoolean("success");
        return code;
    }

    @Override
    protected String getErrorMessage(JSONObject json) throws JSONException {
        String message = json.getString("err");
        return message;
    }

    @Override
    protected String getJsonDataField() {
        return "result";
    }

    @Override
    protected void processMeta(JSONObject json) {
        //TODO: process meta if need
    }
}
