package me.jamesj.openapi.api.http.parameters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Parameter<T> {
    
    Class<T> getType();
    
    @NotNull
    String getName();
    
    boolean isRequired();
    
    @Nullable
    T defaultsTo();
    
    @Nullable
    String getFormat();
    
    @NotNull
    Source getSource();
    
    enum Source {
        HEADERS, PATH, QUERY, BODY;
    }
    
}
