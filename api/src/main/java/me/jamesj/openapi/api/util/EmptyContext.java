package me.jamesj.openapi.api.util;

import me.jamesj.openapi.api.json.Context;

/**
 * Created by James on 20/11/2021
 */

public class EmptyContext implements Context {
    @Override
    public Context flag(IDisplayFlag flag) {
        return this;
    }
    
    @Override
    public boolean hasFlag(IDisplayFlag flag) {
        return false;
    }
    
    @Override
    public Context expand(String key) {
        return this;
    }
}
