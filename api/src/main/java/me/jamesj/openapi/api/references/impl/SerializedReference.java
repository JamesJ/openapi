package me.jamesj.openapi.api.references.impl;

import me.jamesj.openapi.api.HasId;
import me.jamesj.openapi.api.json.JsonProvider;
import me.jamesj.openapi.api.references.Reference;

/**
 * Created by James on 20/11/2021
 */

public class SerializedReference<T extends HasId & JsonProvider> implements Reference<T> {
    
    private final String id;
    private T t;
    
    public SerializedReference(String id) {this.id = id;}
    
    public SerializedReference(T t) {
        if (t == null) {
            throw new NullPointerException("Initializer cannot be null");
        }
        this.id = t.getId();
        this.t = t;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public T get() {
        return t;
    }
    
    @Override
    public boolean isExpanded() {
        return t != null;
    }
    
    @Override
    public String toString() {
        return "SerializedReference{" +
            "id='" + id + '\'' +
            ", t=" + t +
            '}';
    }
}
