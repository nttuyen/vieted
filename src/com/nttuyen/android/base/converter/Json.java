package com.nttuyen.android.base.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: nttuyen
 * Date: 7/2/13
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Json {
    //Json field name
    public String name();

    /**
     * is Collection: only support list
     * @return
     */
    public boolean isCollection() default false;

    /**
     * Type of field in collection
     * @return
     */
    public Class<?> type() default Object.class;
}
