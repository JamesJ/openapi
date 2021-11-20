package me.jamesj.openapi.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Repeatable(KeyValues.class)
public @interface KeyValue {
    
    String key();
    
    String value();
    
}
