package me.jamesj.openapi.api.http.parameters.impl;

import me.jamesj.openapi.api.http.parameters.Parameter;
import org.jetbrains.annotations.NotNull;

public interface QueryParameter<T> extends Parameter<T> {
    @Override
    @NotNull
    default Source getSource() {
        return Source.QUERY;
    }
}
