package com.nttuyen.android.base.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nttuyen266@gmail.com
 */
public class JsonConvertHelper {
    private static final Map<String, JsonConverter> converters = new HashMap<String, JsonConverter>();

    static {
        try {
            Class.forName("com.nttuyen.android.base.json.JsonGenericConverter");
        } catch (ClassNotFoundException e) {}
    }

    public static void register(String name, JsonConverter converter) {
        converters.put(name, converter);
    }

	public static <T> void inject(JSONObject json, T value) throws JSONException {
		Class<?> type = value.getClass();
		JsonConverter converter = converters.get(type.getName());
		if(converter == null) {
			converter = converters.get(JsonGenericConverter.class.getName());
		}
		if(converter == null)
			return;

		converter.inject(json, value);
	}
	public static <T> void inject(JSONArray json, Class<T> type, Collection<T> collection) throws JSONException {
		JsonConverter converter = converters.get(type.getName());
		if(converter == null) {
			converter = converters.get(JsonGenericConverter.class.getName());
		}
		if(converter == null)
			return;

		converter.inject(json, type, collection);
	}
}
