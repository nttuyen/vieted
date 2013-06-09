package com.vieted.android.app.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/8/13
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class VietEdDomain implements Serializable {
    protected long id;

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
