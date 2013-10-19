package com.vieted.android.app.v1.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class Staff implements Serializable {
    private long iid;
    private String name;
    private String avatar;

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
