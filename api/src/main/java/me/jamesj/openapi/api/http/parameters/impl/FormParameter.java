package me.jamesj.openapi.api.http.parameters.impl;

import me.jamesj.openapi.api.http.parameters.Parameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FormParameter<T> extends Parameter<T> {
    
    static Form<String> field(String field) {
        return new Form<>(String.class, field);
    }
    
    @Override
    @NotNull
    default Source getSource() {
        return Source.BODY;
    }
    
    static class Form<T> implements FormParameter<T> {
        
        private final String field;
        private final Class<T> type;
        private T defaultsTo;
        private String format;
        private boolean required = false;
        
        private Form(Class<T> type, String field) {
            this.type = type;
            this.field = field;
        }
        
        public Form<T> required() {
            this.required = true;
            return this;
        }
        
        public <N> Form<N> type(Class<N> clazz) {
            Form<N> newForm = new Form<>(clazz, this.field);
            if (required) {
                newForm.required();
            }
            if (format != null) {
                newForm.format(format);
            }
            return newForm;
        }
        
        @Override
        public Class<T> getType() {
            return this.type;
        }
        
        @Override
        public @NotNull String getName() {
            return this.field;
        }
        
        @Override
        public boolean isRequired() {
            return this.required;
        }
        
        @Override
        public @Nullable T defaultsTo() {
            return this.defaultsTo;
        }
        
        public Form<T> defaultsTo(T t) {
            this.defaultsTo = t;
            return this;
        }
        
        @Override
        public @Nullable String getFormat() {
            return this.format;
        }
        
        public Form<T> format(String format) {
            this.format = format;
            return this;
        }
    }
}
