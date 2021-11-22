package example;

import example.routes.ComplexRoute;
import me.jamesj.openapi.annotations.KeyValue;
import me.jamesj.openapi.api.http.HttpMethod;
import me.jamesj.openapi.api.http.HttpRoute;
import me.jamesj.openapi.generator.wrappers.impl.RouteWrapper;
import org.junit.Test;

import java.util.Map;

/**
 * Created by James on 20/11/2021
 */

@KeyValue(key = "x-routeId", value = "example_http_route")
public class ExampleHttpRoute implements HttpRoute<String> {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
    
    @Override
    public String getPath() {
        return "/example/string";
    }
    
    @Test
    public void test() {
        RouteWrapper<Map<String, Object>> routeWrapper = new RouteWrapper<>(new ComplexRoute());
        
        System.out.println(routeWrapper.toJson().toString());
    }
}
