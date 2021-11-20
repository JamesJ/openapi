package me.jamesj.openapi.generator.wrappers.impl;

import com.google.gson.*;
import me.jamesj.openapi.annotations.routes.Produces;
import me.jamesj.openapi.api.http.HttpMethod;
import me.jamesj.openapi.api.http.HttpRoute;
import me.jamesj.openapi.generator.wrappers.AbstractWrapper;

import java.lang.reflect.Type;

/**
 * Created by James on 20/11/2021
 */

public class RouteWrapper<T> extends AbstractWrapper {
    
    private final HttpRoute<T> route;
    
    public RouteWrapper(HttpRoute<T> route) {
        super(route.getClass());
        
        this.route = route;
    }
    
    public HttpMethod getMethod() {
        return route.getHttpMethod();
    }
    
    public String getPath() {
        return route.getPath();
    }
    
    // this is truly hideous, need to figure out a nicer way of doing this
    @Override
    protected void appendJson(JsonObject jsonObject) {
        jsonObject.addProperty("operationId", route.getOperationId());
        JsonObject def = new JsonObject();
        
        String produces = "*/*";
        if (route.getClass().isAnnotationPresent(Produces.class)) {
            produces = route.getClass().getAnnotation(Produces.class).value();
        }
        
        JsonObject type = new JsonObject();
        //todo: fix schema name
        type.add(produces, of("schema", of("$ref", new JsonPrimitive("#/components/schemas/" + getName()))));
        
        def.add("default", type);
        
        jsonObject.add("responses", def);
    }
    
    private String getTypeName(Type type) {
        return type.getTypeName();
    }
    
    private JsonObject of(String key, JsonElement element) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key, element);
        return jsonObject;
    }
}
