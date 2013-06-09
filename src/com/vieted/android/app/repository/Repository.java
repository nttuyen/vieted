package com.vieted.android.app.repository;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/8/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Repository {
    public <T> T getById(Class<T> type, long id);
}
