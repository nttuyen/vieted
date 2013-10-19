package com.vieted.android.app.v1.dto;

import com.nttuyen.android.base.converter.Json;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/7/13
 * Time: 8:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class User implements Serializable{
    private String id;
    @Json(name = "lname")
    private String username;
    private String name;
    @Json(name = "mail")
    private String email;
    private String avatar;
    private String uhash;
    private String status;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUhash() {
        return uhash;
    }

    public void setUhash(String uhash) {
        this.uhash = uhash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
