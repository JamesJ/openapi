package me.jamesj.openapi.api.http;

import me.jamesj.openapi.api.http.parameters.Parameter;

import java.util.Collections;
import java.util.List;

/**
 * Created by James on 20/11/2021
 */

public interface HttpRoute<T> {
    
    HttpMethod getHttpMethod();
    
    String getOperationId();
    
    String getPath();
    
    default List<Parameter<?>> getParameters() {
        return Collections.emptyList();
    }
    
}
