package com.nttuyen.android.base.request;

import java.util.HashMap;
import java.util.Map;

public abstract class GenericRequest implements Request {
    protected final int method;
    protected Map<String, String> params;

    public GenericRequest() {
        this(Request.HTTP_GET);
    }

    public GenericRequest(int method) {
        this.method = method;
        this.params = new HashMap<String, String>();
    }

    @Override
    public int getMethod() {
        return this.method;
    }

    @Override
    public Map<String, String> getParameters() {
        return this.params;
    }

    @Override
    public String getParameter(String name) {
        return this.params.get(name);
    }

    @Override
    public void setParameter(String name, String value) {
        this.params.put(name, value);
    }

    @Override
    public void setParameters(Map<String, String> parameters) {
        this.params = parameters;
    }
}
