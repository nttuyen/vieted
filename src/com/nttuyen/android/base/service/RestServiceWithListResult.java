package com.nttuyen.android.base.service;

import com.nttuyen.android.base.converter.JsonConvertHelper;
import com.nttuyen.android.base.converter.JsonConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public abstract class RestServiceWithListResult<Result> extends RestService<List<Result>>{
    protected final Class<Result> type;

    public RestServiceWithListResult(Class<Result> type) {
        this.type = type;
    }

    protected abstract JSONArray getJsonData(JSONObject jsonObject) throws JSONException;

    @Override
    protected List<Result> getData(JSONObject json) throws JSONException {
        return JsonConvertHelper.convert(this.getJsonData(json), this.type);
    }
}
