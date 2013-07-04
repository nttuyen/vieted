package com.nttuyen.android.base.service;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 6/28/13
 * Time: 9:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String detailMessage) {
        super(detailMessage);
    }

    public ServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
