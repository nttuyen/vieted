package com.nttuyen.android.base.service;

import com.nttuyen.android.base.converter.JsonConvertHelper;
import com.nttuyen.android.base.converter.JsonConverter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/1/13
 * Time: 10:31 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class RestServicesWithSingleResult<Result> extends RestService<Result> {
    protected final Class<Result> type;

    public RestServicesWithSingleResult(Class<Result> type) {
        super();
        this.type = type;
    }

    protected abstract JSONObject getJsonData(JSONObject json) throws JSONException;

    @Override
    protected Result getData(JSONObject json) throws JSONException {
        return JsonConvertHelper.convert(this.getJsonData(json), type);
    }
}
