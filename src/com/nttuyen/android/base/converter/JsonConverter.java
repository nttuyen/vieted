package com.nttuyen.android.base.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/28/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class JsonConverter {
    public abstract <T> T convert(JSONObject json, Class<T> type) throws JSONException;

    public <T> List<T> convert(JSONArray json, Class<T> type) throws JSONException {
        List<T> values = new ArrayList<T>();
        int size = json.length();
        for(int i = 0; i< size; i++) {
            JSONObject jsonObject = json.getJSONObject(i);
            T val = this.convert(jsonObject, type);
            values.add(val);
        }
        return values;
    }
}
