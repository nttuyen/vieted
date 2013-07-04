package com.nttuyen.android.base.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/28/13
 * Time: 10:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonConvertHelper {
    private static final Map<String, JsonConverter> converters = new HashMap<String, JsonConverter>();

    static {
        try {
            Class.forName("com.nttuyen.android.base.converter.JsonGenericConverter");
        } catch (ClassNotFoundException e) {}
    }

    public static void register(String name, JsonConverter converter) {
        converters.put(name, converter);
    }

    public static <T> T convert(JSONObject json, Class<T> type) throws JSONException {
        JsonConverter converter = converters.get(type.getName());
        if(converter == null) {
            converter = converters.get(JsonGenericConverter.class.getName());
        }
        if(converter == null)
            return null;

        return converter.convert(json, type);
    }

    public static <T> List<T> convert(JSONArray json, Class<T> type) throws JSONException {
        JsonConverter converter = converters.get(type.getName());
        if(converter == null) {
            converter = converters.get(JsonGenericConverter.class.getName());
        }
        if(converter == null)
            return Collections.EMPTY_LIST;

        return converter.convert(json, type);
    }
}
