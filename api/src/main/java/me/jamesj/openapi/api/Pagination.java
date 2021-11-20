package me.jamesj.openapi.api;

import java.util.List;

/**
 * Created by James on 20/11/2021
 */

public interface Pagination<T extends HasId> extends HasId {
    
    List<T> list();
    
}
