package example;

import me.jamesj.openapi.api.http.parameters.Parameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by James on 20/11/2021
 */

public class ExampleParameter implements Parameter<String> {
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
