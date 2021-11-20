package me.jamesj.openapi.api.references;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.jamesj.openapi.api.HasId;
import me.jamesj.openapi.api.json.Context;
import me.jamesj.openapi.api.json.JsonProvider;

public interface Reference<T extends JsonProvider & HasId> extends JsonProvider, HasId {
    
    T get();
    
    boolean isExpanded();
    
    @Override
    default JsonElement toJson(Context context) {
        if (isExpanded()) {
            return get().toJson(context);
        }
        return new JsonPrimitive(getId());
    }
}