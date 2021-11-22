package me.jamesj.openapi.generator.wrappers.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.jamesj.openapi.annotations.routes.Consumes;
import me.jamesj.openapi.annotations.routes.Produces;
import me.jamesj.openapi.api.http.HttpMethod;
import me.jamesj.openapi.api.http.HttpRoute;
import me.jamesj.openapi.api.http.parameters.Parameter;
import me.jamesj.openapi.generator.util.JsonArrayCollector;
import me.jamesj.openapi.generator.wrappers.AbstractWrapper;

import java.lang.reflect.Type;
import java.util.Arrays;

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
    
    @Override
    protected void appendJson(JsonObject jsonObject) {
        jsonObject.addProperty("fqdn", route.getClass().getName());
        jsonObject.addProperty("method", route.getHttpMethod().name());
        jsonObject.addProperty("path", route.getPath());
        
        Produces produces;
        if ((produces = route.getClass().getAnnotation(Produces.class)) != null) {
            jsonObject.add("produces", Arrays.stream(produces.value()).collect(new JsonArrayCollector()));
        }
        Consumes consumes;
        if ((consumes = route.getClass().getAnnotation(Consumes.class)) != null) {
            jsonObject.add("consumes", Arrays.stream(consumes.value()).collect(new JsonArrayCollector()));
        }
        
        jsonObject.addProperty("returns", "#/models/" + getName());
        
        JsonArray parameters = new JsonArray();
        for (Parameter<?> parameter : route.getParameters()) {
            parameters.add(new ParameterWrapper<>(parameter).toJson());
        }
        
        jsonObject.add("parameters", parameters);
    }
    
    private String getTypeName(Type type) {
        return type.getTypeName();
    }
    
}
