package com.nttuyen.android.base.converter;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/2/13
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConverterException extends Exception {
    public ConverterException() {
        super();
    }

    public ConverterException(String detailMessage) {
        super(detailMessage);
    }

    public ConverterException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ConverterException(Throwable throwable) {
        super(throwable);
    }
}
