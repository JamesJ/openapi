package me.jamesj.openapi.generator;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by James on 20/11/2021
 */

public class Generator {
    
    private final List<Class<?>> classes;
    
    public Generator(@NotNull List<Class<?>> classes) {
        this.classes = classes;
    }
    
    public Generator(@NotNull Class<?>... classes) {
        this(Arrays.asList(classes));
    }
    
    public void execute() {
    
    }
}
