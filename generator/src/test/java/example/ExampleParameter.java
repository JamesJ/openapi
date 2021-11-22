package example;

import me.jamesj.openapi.api.http.parameters.Parameter;
import me.jamesj.openapi.api.http.parameters.impl.FormParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by James on 20/11/2021
 */

public class ExampleParameter implements FormParameter<String> {
    @Override
    public Class<String> getType() {
        return String.class;
    }
    
    @Override
    public @NotNull String getName() {
        return "example";
    }
    
    @Override
    public boolean isRequired() {
        return true;
    }
    
    @Override
    public @Nullable String defaultsTo() {
        return null;
    }
    
    @Override
    public @Nullable String getFormat() {
        return null;
    }
}
