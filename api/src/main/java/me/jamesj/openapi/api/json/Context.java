package me.jamesj.openapi.api.json;

public interface Context {
    
    Context flag(IDisplayFlag flag);
    
    boolean hasFlag(IDisplayFlag flag);
    
    Context expand(String key);
    
    interface IDisplayFlag {}
    
}
