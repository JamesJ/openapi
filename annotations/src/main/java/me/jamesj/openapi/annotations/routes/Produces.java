package me.jamesj.openapi.annotations.routes;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Produces {
    
    @NotNull
    String value();
    
}
