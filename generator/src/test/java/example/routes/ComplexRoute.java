package example.routes;

import me.jamesj.openapi.annotations.Description;
import me.jamesj.openapi.annotations.KeyValue;
import me.jamesj.openapi.api.http.HttpMethod;
import me.jamesj.openapi.api.http.HttpRoute;
import me.jamesj.openapi.api.http.parameters.Parameter;
import me.jamesj.openapi.api.http.parameters.impl.FormParameter;

import java.util.*;

/**
 * Created by James on 20/11/2021
 */

@Description("A complex route to test the **ability** of the parser")
@KeyValue(key = "x-randomKeyValue", value = "true")
@KeyValue(key = "x-randomKeyValue2", value = "hello")
@KeyValue(key = "x-randomKeyValue3", value = "howdy")
public class ComplexRoute implements HttpRoute<Map<String, Object>> {
    
    private static final Parameter<String> NAME = FormParameter.field("name").required().defaultsTo("Jimmy");
    private static final Parameter<Boolean> KEEP_LOGGED_IN = FormParameter.field("keep_logged_in")
        .type(boolean.class).defaultsTo(false).required();
    
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
    
    @Override
    public String getPath() {
        return "/routes/complex";
    }
    
    @Override
    public List<Parameter<?>> getParameters() {
        return Arrays.asList(NAME, KEEP_LOGGED_IN);
    }
}
