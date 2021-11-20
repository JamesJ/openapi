package me.jamesj.openapi.api.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import me.jamesj.openapi.api.references.Reference;
import me.jamesj.openapi.api.references.serializer.ReferenceSerializer;

public interface JsonProvider {
    
    
    Gson GSON = new Gson()
        .newBuilder()
        .serializeNulls()
        .registerTypeAdapter(Reference.class, new ReferenceSerializer())
        .create();
    
    JsonElement toJson(Context context);
    
}
