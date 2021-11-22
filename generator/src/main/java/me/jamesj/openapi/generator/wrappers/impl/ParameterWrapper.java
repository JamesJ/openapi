package me.jamesj.openapi.generator.wrappers.impl;

import com.google.gson.JsonObject;
import me.jamesj.openapi.api.http.parameters.Parameter;
import me.jamesj.openapi.api.http.parameters.impl.FormParameter;
import me.jamesj.openapi.generator.wrappers.AbstractWrapper;
import org.jetbrains.annotations.NotNull;

/**
 * Created by James on 22/11/2021
 */

public class ParameterWrapper<T> extends AbstractWrapper {
    private final Parameter<T> parameter;
    
    public ParameterWrapper(@NotNull Parameter<T> parameter) {
        super(parameter.getClass());
        
        this.parameter = parameter;
    }
    
    @Override
    protected void appendJson(JsonObject jsonObject) {
        jsonObject.addProperty("field", parameter.getName());
        jsonObject.addProperty("type", parameter.getType().getName()); //todo: cover this
        jsonObject.addProperty("required", parameter.isRequired());
        if (parameter.getFormat() != null) {
            jsonObject.addProperty("format", parameter.getFormat());
        }
        jsonObject.addProperty("source", parameter.getSource().name());
        if (parameter.defaultsTo() != null) {
            jsonObject.add("defaults_to", toJsonElement(parameter.defaultsTo()));
        }
    }
}
