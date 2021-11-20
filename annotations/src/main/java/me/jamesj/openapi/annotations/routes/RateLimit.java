package me.jamesj.openapi.annotations.routes;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RateLimit {
    
    @NotNull
    int value();
    
    String group() default "general";
    
    int period() default 60; // in minutes
    
}
