package example.routes;

import example.ExampleParameter;
import me.jamesj.openapi.annotations.Description;
import me.jamesj.openapi.annotations.KeyValue;
import me.jamesj.openapi.api.http.HttpMethod;
import me.jamesj.openapi.api.http.HttpRoute;
import me.jamesj.openapi.api.http.parameters.Parameter;

import java.util.*;

/**
 * Created by James on 20/11/2021
 */

@Description("A complex route to test the **ability** of the parser")
@KeyValue(key = "x-randomKeyValue", value = "true")
@KeyValue(key = "x-randomKeyValue2", value = "hello")
@KeyValue(key = "x-randomKeyValue3", value = "howdy")
public class ComplexRoute implements HttpRoute<Map<String, Object>> {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
    
    @Override
    public String getOperationId() {
        return "complexHttpRoute";
    }
    
    @Override
    public String getPath() {
        return "/routes/complex";
    }
    
    @Override
    public List<Parameter<?>> getParameters() {
        return Arrays.asList(new ExampleParameter());
    }
}
