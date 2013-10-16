package com.nttuyen.android.base.request;

import java.util.Map;

public interface Request {
    public static final int HTTP_GET = 1;
    public static final int HTTP_POST = 2;

    public String getURL();
    public int getMethod();

    public Map<String, String> getParameters();
    public String getParameter(String name);
    public void setParameter(String name, String value);
    public void setParameters(Map<String, String> parameters);
}
