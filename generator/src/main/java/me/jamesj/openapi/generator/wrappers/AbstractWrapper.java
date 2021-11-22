package me.jamesj.openapi.generator.wrappers;

import com.google.gson.*;
import me.jamesj.openapi.annotations.*;
import me.jamesj.openapi.generator.util.JsonArrayCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;

/**
 * Created by James on 20/11/2021
 */

public abstract class AbstractWrapper {
    
    private final AnnotatedElement element;
    
    private final Map<String, Object> metadata;
    private final String[] tags;
    
    protected AbstractWrapper(AnnotatedElement element) {
        this.element = element;
        
        this.metadata = new LinkedHashMap<>();
        
        if (element.isAnnotationPresent(KeyValue.class)) {
            KeyValue keyValue = element.getAnnotation(KeyValue.class);
            appendKeyValue(keyValue);
        } else if (element.isAnnotationPresent(KeyValues.class)) {
            KeyValues keyValues = element.getAnnotation(KeyValues.class);
            for (KeyValue keyValue : keyValues.value()) {
                appendKeyValue(keyValue);
            }
        }
        
        if (element.isAnnotationPresent(Tags.class)) {
            this.tags = element.getAnnotation(Tags.class).value();
        } else {
            this.tags = new String[0];
        }
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    private void appendKeyValue(KeyValue keyValue) {
        this.metadata.put(keyValue.key(), keyValue.value());
    }
    
    public String getName() {
        String def = element.getClass().getSimpleName();
        if (element instanceof Field) {
            def = ((Field) element).getName();
        }
        return getOrDefault(Name.class, Name::value, element.getClass().getName());
    }
    
    public String getDescription() {
        return getOrDefault(Description.class, Description::value, null);
    }
    
    protected void appendJson(JsonObject jsonObject) {
    }
    
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        
        String description = getDescription();
        if (description != null) {
            jsonObject.addProperty("description", description);
        }
        
        if (tags != null && tags.length > 0) {
            jsonObject.add("tags", Arrays.stream(tags).collect(new JsonArrayCollector()));
        }
        
        appendMetadata(jsonObject);
        appendJson(jsonObject);
        return jsonObject;
    }
    
    private Map<String, Object> appendMetadata(JsonObject jsonObject, Map<String, Object> map, String s, Object value) {
        String key = s;
        if (s.indexOf(".") >= 0) {
            key = s.substring(0, s.indexOf("."));
            
            Map<String, Object> sub;
            if (map.containsKey(s)) {
                Object existing = map.get(s);
                if (existing instanceof Map) {
                    sub = (Map<String, Object>) existing;
                } else {
                    throw new IllegalStateException("Incompatible mix and match of map types!");
                }
            } else {
                sub = new HashMap<>();
            }
            map.put(key, appendMetadata(jsonObject, sub, key, value));
            return map;
        } else {
            key = s;
        }
        
        if (map.containsKey(key)) {
            Object val = map.containsKey(key);
            if (val instanceof JsonArray) {
                JsonArray array = (JsonArray) val;
                array.add(toJsonElement(value));
                
                map.put(key, array);
            } else if (val instanceof JsonObject) {
                throw new IllegalStateException("Incompatible mix and match of types!");
            } else {
                JsonArray array = new JsonArray();
                array.add(toJsonElement(val));
                array.add(toJsonElement(value));
                
                map.put(key, array);
            }
            
        } else {
            map.put(key, value);
        }
        
        return map;
    }
    
    protected JsonElement toJsonElement(Object object) {
        if (object instanceof String) {
            return new JsonPrimitive((String) object);
        }
        if (object instanceof Number) {
            return new JsonPrimitive((Number) object);
        }
        if (object instanceof Character) {
            return new JsonPrimitive((Character) object);
        }
        if (object instanceof Boolean) {
            return new JsonPrimitive((Boolean) object);
        }
        throw new IllegalStateException("Unknown value");
    }
    
    private void appendMetadata(JsonObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        
        //todo: nested objects
        for (String s : metadata.keySet()) {
            jsonObject.add(s, toJsonElement(metadata.get(s)));
        }
    }
    
    private <T extends Annotation> String getOrDefault(@NotNull Class<T> annotationClass, @NotNull Function<T, String> function, @Nullable String def) {
        T annotation = element.getAnnotation(annotationClass);
        if (annotation == null) {
            return def;
        }
        String str = function.apply(annotation);
        if (str == null) {
            return def;
        }
        return str;
    }
}
