package com.nttuyen.android.base.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

/**
 * @author nttuyen266@gmail.com
 */
public abstract class JsonConverter {
	/**
	 * Inject to object
	 * @param json
	 * @param value
	 * @param <T>
	 * @throws JSONException
	 */
	public abstract <T> void inject(JSONObject json, T value) throws JSONException;
	public <T> void inject(JSONArray json, Class<T> type, Collection<T> collections) throws JSONException {
		if(collections == null || type == null || json == null) {
			return;
		}
		try {
			int size = json.length();
			for(int i = 0; i< size; i++) {
				if(isPrimaryType(type)) {
					collections.add(getValueInJsonArray(json, i, type));
				} else {
					JSONObject jsonObject = json.getJSONObject(i);
					T val = type.newInstance();
					this.inject(jsonObject, val);
					collections.add(val);
				}
			}
		} catch (Exception ex) {

		}
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
