package com.vieted.android.app.request;

import com.nttuyen.android.base.request.GenericRequest;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/25/13
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class VietEdRequest extends GenericRequest {
    protected final String url;
    public VietEdRequest(String url, int method) {
        super(method);
        this.url = url;
    }

    @Override
    public String getURL() {
        return this.url;
    }
}
