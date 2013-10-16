package com.nttuyen.android.base.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class JsonConverter {
	public abstract <T> void fromJson(JSONObject json, T value) throws JSONException;

	public <T> T convert(JSONObject json, Class<T> type) throws JSONException {
		if(isPrimaryType(type)) {
			throw new UnsupportedOperationException("Can not convert to type: " + type.getName());
		}

		try {
			T value = type.newInstance();
			this.fromJson(json, value);
			return value;

		} catch (InstantiationException e) {
			JSONException ex = new JSONException("can not create new instance");
			ex.initCause(e);
			throw ex;
		} catch (IllegalAccessException e) {
			JSONException ex = new JSONException("can not create new instance");
			ex.initCause(e);
			throw ex;
		}
	}

	public <T> void fromJson(JSONArray json, Class<T> type, List<T> list) throws JSONException{
		int size = json.length();
		for(int i = 0; i< size; i++) {
			if(isPrimaryType(type)) {
				list.add(getValueInJsonArray(json, i, type));
			} else {
				JSONObject jsonObject = json.getJSONObject(i);
				T val = this.convert(jsonObject, type);
				list.add(val);
			}
		}
	}

    public <T> List<T> convert(JSONArray json, Class<T> type) throws JSONException {
        List<T> values = new ArrayList<T>();
		this.fromJson(json, type, values);
		return values;
    }

    protected  <T> T getValueInJsonArray(JSONArray jsonArray, int index, Class<T> type) throws JSONException {
        if(Boolean.TYPE.equals(type)) {
            return type.cast(jsonArray.getBoolean(index));
        } else if(Double.TYPE.equals(type)) {
            return type.cast(jsonArray.getDouble(index));
        } else if(Integer.TYPE.equals(type)) {
            return type.cast(jsonArray.getInt(index));
        } else if(Long.TYPE.equals(type)) {
            return type.cast(jsonArray.getLong(index));
        } else if(String.class.equals(type)) {
            return type.cast(jsonArray.getString(index));
        } else if(JSONObject.class.equals(type)) {
            return (T)jsonArray.getJSONObject(index);
        } else if(JSONArray.class.equals(type)) {
            return (T)jsonArray.getJSONArray(index);
        }
        return null;
    }

    protected boolean isPrimaryType(Class type) {
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
