package com.vieted.android.app.v1.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class Video implements Serializable {
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
