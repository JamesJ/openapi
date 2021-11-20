package me.jamesj.openapi.annotations.models;

import java.lang.annotation.*;

/**
 * Created by @James on 07/08/2021
 *
 * @author James
 * @since 07/08/2021
 */
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Documentation {

    String value();
}
