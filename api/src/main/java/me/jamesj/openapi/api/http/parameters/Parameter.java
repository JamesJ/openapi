package me.jamesj.openapi.api.http.parameters;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Parameter<T> {
    
    @NotNull
    String getName();
    
    boolean isRequired();
    
    @Nullable
    T defaultsTo();
    
    @Nullable
    String getFormat();
    
}
