package com.vieted.android.app.utils;

import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/25/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface JsonConverter<T> {
    public T convert(JSONObject json);
}
