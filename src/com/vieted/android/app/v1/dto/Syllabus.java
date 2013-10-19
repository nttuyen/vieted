package com.vieted.android.app.v1.dto;

import com.nttuyen.android.base.converter.Json;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class Syllabus {
    private String iid;
    @Json(name = "units", isCollection = true, type = Unit.class)
    private List<Unit> units;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }
}
