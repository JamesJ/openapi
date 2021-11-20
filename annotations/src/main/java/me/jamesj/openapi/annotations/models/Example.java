package me.jamesj.openapi.annotations.models;

import java.lang.annotation.*;

/**
 * Created by @James on 07/08/2021
 *
 * @author James
 * @since 07/08/2021
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Example {

    String value();
}
