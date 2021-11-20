package me.jamesj.openapi.generator.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by James on 20/11/2021
 */

public class AnnotationFetcher {
    
    private AnnotationFetcher() {
    }
    
    public static <T extends Annotation> Optional<String> fetch(AnnotatedElement element, Class<T> annotationClass, Function<T, String> function) {
        T annotation = element.getAnnotation(annotationClass);
        if (annotation != null) {
            Optional.ofNullable(function.apply(annotation));
        }
        return Optional.empty();
    }
    
}
