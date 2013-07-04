package com.vieted.android.app.utils;

import com.vieted.android.app.domain.Lesson;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/25/13
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericJsonConverter<T> implements JsonConverter<T> {
    private final Class<T> type;
    public GenericJsonConverter() {
        this.type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Override
    public T convert(JSONObject json) {

        if(this.type == Lesson.class) {
            return null;
        }
        return null;
    }
}
