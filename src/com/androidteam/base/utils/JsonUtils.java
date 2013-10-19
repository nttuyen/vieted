package com.androidteam.base.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/19/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
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
    
    public static JSONObject getJsonObjectResult(JSONObject jsonObject){
    	try {
			JSONObject temp = jsonObject.getJSONObject("result");
			return temp;
    		
		} catch (Exception e) {
			// TODO: execute exception
			return null;
		}
    	
    }
    
    public static JSONArray getJsonArrayResult(JSONObject jsonObject){
    	try {
			JSONArray temp = jsonObject.getJSONArray("result");
			return temp;
    		
		} catch (Exception e) {
			// TODO: execute exception
			return null;
		}
    	
    }
    
 
}
