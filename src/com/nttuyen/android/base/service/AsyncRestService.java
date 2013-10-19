package com.nttuyen.android.base.service;

import com.nttuyen.android.base.converter.JsonConvertHelper;
import com.nttuyen.android.base.request.Request;
import com.nttuyen.android.base.request.RequestHelper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/6/13
 * Time: 9:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public abstract class AsyncRestService<Result> extends AsyncService<Request, Result> {
    private static final HttpClient httpClient = new DefaultHttpClient();
    static {
        //TODO: init httpClient here
    }

    private final Class<?> type;
    public AsyncRestService(Class<?> type) {
        this.type = type;
    }

    protected abstract boolean checkSuccess(JSONObject json) throws JSONException;
    protected abstract String getMessage(JSONObject json) throws JSONException;
    protected abstract String getJsonDataField();
    protected abstract void processMeta(JSONObject json);

    @Override
    protected Result execute(Request request) throws ServiceException {
        try {
            String response = RequestHelper.execute(httpClient, request);
            //TODO: temp for pass test case, we need request to change api at server
            //
            if(response.charAt(0) == '(') {
                response = response.substring(1);
            }
            int length = response.length();
            if(response.charAt(length -1) == ')') {
                response = response.substring(0, length - 1);
            }

            JSONObject json = new JSONObject(response);

            //TODO: process meta from Rest rusult
            processMeta(json);

            if(!checkSuccess(json)) {
                String message = getMessage(json);
                AsyncRestService.this.exception = new ServiceException(message);
                this.result = null;
                return this.result;
            }

            String jsonDataField = this.getJsonDataField();
            if(!json.has(jsonDataField)) {
                AsyncRestService.this.exception = new ServiceException("Data format error!");
                this.result = null;
                return this.result;
            }

            if(json.isNull(jsonDataField)) {
                this.result = null;
                return this.result;
            }

            //TODO: process primary type here
            if(isPrimaryType(this.type)) {
                this.result = (Result)this.getJsonValue(json, jsonDataField, this.type);
                return this.result;
            }

            try {
                //Return object
                JSONObject jsonObject = json.getJSONObject(jsonDataField);
                this.result = (Result)JsonConvertHelper.convert(jsonObject, this.type);
            } catch (JSONException e) {
                //Return Collection
                //TODO: only support List type
                JSONArray jsonArray = json.getJSONArray(jsonDataField);
                this.result = (Result)JsonConvertHelper.convert(jsonArray, this.type);
            }

            return this.result;
        } catch (IOException e) {
            AsyncRestService.this.exception = new ServiceException("Error on execution request", e);
        } catch (JSONException e) {
            AsyncRestService.this.exception = new ServiceException("Data format error!", e);
        }

        this.result = null;
        return this.result;
    }

    private <T> T getJsonValue(JSONObject json, String fieldName, Class<T> type) throws JSONException {
        if(Boolean.TYPE.equals(type)) {
            return type.cast(json.getBoolean(fieldName));
        } else if(Double.TYPE.equals(type)) {
            return type.cast(json.getDouble(fieldName));
        } else if(Integer.TYPE.equals(type)) {
            return type.cast(json.getInt(fieldName));
        } else if(Long.TYPE.equals(type)) {
            return type.cast(json.getLong(fieldName));
        } else if(String.class.equals(type)) {
            return type.cast(json.getString(fieldName));
        } else if(JSONObject.class.equals(type)) {
            return (T)json.getJSONObject(fieldName);
        } else if(JSONArray.class.equals(type)) {
            return (T)json.getJSONArray(fieldName);
        }

        throw new JSONException("Primary type: " + type.getName() + " is not supported");
    }

    private boolean isPrimaryType(Class type) {
        return Boolean.TYPE.equals(type)
                || Double.TYPE.equals(type)
                || Integer.TYPE.equals(type)
                || Long.TYPE.equals(type)
                || String.class.equals(type)
                || JSONObject.class.equals(type)
                || JSONArray.class.equals(type)
                ;
    }
}
