package me.jamesj.openapi.annotations.routes;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Consumes {
    
    @NotNull
    String value();
    
    static interface ContentType {
        String X_WWW_URL_ENCODED_FORM = "application/x-www-form-urlencoded";
        String FORM = "multipart/form-data";
    }
    
}
