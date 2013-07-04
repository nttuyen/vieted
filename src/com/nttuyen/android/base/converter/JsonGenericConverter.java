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

    public <T> T convert(JSONObject json, Class<T> type) throws JSONException {
        try {
            T value = type.newInstance();

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
                    int size = jsonArray.length();
                    List<Object> list = new ArrayList<Object>(size);
                    for(int i = 0; i < size; i++) {
                        if(isPrimaryType(typeInList)) {
                            list.add(getValueInJsonArray(jsonArray, i, typeInList));
                        } else {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Object obj = convert(jsonObject, typeInList);
                            list.add(obj);
                        }
                    }
                    if(!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(value, list);
                    continue;
                }

                setField(value, field, jsonField, json);

            }
            return value;
        } catch (InstantiationException e) {
            JSONException ex = new JSONException("Can not create new instance");
            ex.initCause(e);
            throw ex;
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
    private <T> T getValueInJsonArray(JSONArray jsonArray, int index, Class<T> type) throws JSONException {
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
