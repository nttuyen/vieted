package com.nttuyen.android.base.json;

/**
 * @author nttuyen266@gmail.com
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
