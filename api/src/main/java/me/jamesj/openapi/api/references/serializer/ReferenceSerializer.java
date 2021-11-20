package me.jamesj.openapi.api.references.serializer;

import com.google.gson.*;
import me.jamesj.openapi.api.HasId;
import me.jamesj.openapi.api.json.JsonProvider;
import me.jamesj.openapi.api.references.Reference;
import me.jamesj.openapi.api.references.impl.SerializedReference;
import me.jamesj.openapi.api.util.EmptyContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by James on 20/11/2021
 */

public class ReferenceSerializer implements JsonDeserializer<Reference<?>>, JsonSerializer<Reference<?>> {
    
    @Override
    public Reference<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject fieldAsJsonObject = json.getAsJsonObject();
            
            Type clazz = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            return new SerializedReference<>((JsonProvider & HasId) context.deserialize(fieldAsJsonObject, clazz));
        } else {
            JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
            if (jsonPrimitive.isString()) {
                return new SerializedReference<>(json.getAsString());
            } else {
                throw new IllegalStateException("Field is not a string");
            }
        }
    }
    
    @Override
    public JsonElement serialize(Reference<?> src, Type typeOfSrc, JsonSerializationContext context) {
        return src.toJson(new EmptyContext());
    }
}

