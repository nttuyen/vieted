package com.nttuyen.android.base.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JsonGenericConverter extends JsonConverter {

    static {
        JsonConvertHelper.register(JsonGenericConverter.class.getName(), new JsonGenericConverter());
    }

	public <T> void fromJson(JSONObject json, T value) throws JSONException {
		try {
			Class<?> type = value.getClass();

			Field[] fields = type.getDeclaredFields();
			for(Field field : fields) {
				String jsonField = field.getName();
				Json annotation = field.getAnnotation(Json.class);
				if(annotation != null) {
					jsonField = annotation.name();
				}
				if(json.isNull(jsonField)) {
					continue;
				}

				if(annotation!= null && annotation.isCollection()) {
					//TODO: only support for ListType
					if(!field.getType().equals(List.class)) {
						continue;
					}

					Class<?> typeInList = annotation.type();
					JSONArray jsonArray = json.getJSONArray(jsonField);

					List<?> list = this.convert(jsonArray, typeInList);

					if(!field.isAccessible()) {
						field.setAccessible(true);
					}
					field.set(value, list);
					continue;
				}

				setField(value, field, jsonField, json);

			}
			//return value;
		} catch (IllegalAccessException e) {
			JSONException ex = new JSONException("Can not create new instance");
			ex.initCause(e);
			throw ex;
		}
	}

    private <T> void setField(T value, Field field, String jsonField, JSONObject json) throws JSONException {
        try {
            if(!field.isAccessible()) {
                field.setAccessible(true);
            }
            Class<?> type = field.getType();

            if(Boolean.TYPE.equals(type)) {
                field.setBoolean(value, json.optBoolean(jsonField));
                return;
            } else if(Double.TYPE.equals(type)) {
                field.setDouble(value, json.optDouble(jsonField));
                return;
            } else if(Integer.TYPE.equals(type)) {
                field.setInt(value, json.optInt(jsonField));
                return;
            } else if(Long.TYPE.equals(type)) {
                field.setLong(value, json.optLong(jsonField));
                return;
            } else if(String.class.equals(type)) {
                field.set(value, json.optString(jsonField));
                return;
            } else if(JSONObject.class.equals(type)) {
                field.set(value, json.optJSONObject(jsonField));
                return;
            } else if(JSONArray.class.equals(type)) {
                field.set(value, json.optJSONArray(jsonField));
                return;
            }

            try {
                JSONObject jsonObject = json.getJSONObject(jsonField);
                Object obj = this.convert(jsonObject, type);
                field.set(value, obj);
                return;
            } catch (JSONException ex) {
                field.set(value, json.opt(jsonField));
                return;
            }
        } catch (IllegalAccessException e) {
            JSONException ex = new JSONException("Can not set value to field");
            ex.initCause(e);
            throw ex;
        }
    }
}
