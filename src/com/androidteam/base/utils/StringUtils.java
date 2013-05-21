package com.androidteam.base.utils;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 5/19/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    public static boolean isStringEmpty(String value) {
        return value == null || "".equals(value.trim());
    }
}
