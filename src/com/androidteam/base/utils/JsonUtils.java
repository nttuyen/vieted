package com.androidteam.base.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/19/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtils {
    public static boolean getReturnStatus(JSONObject jsonObject) {
        try {
            Boolean code = jsonObject.getBoolean("success");
            return code;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
