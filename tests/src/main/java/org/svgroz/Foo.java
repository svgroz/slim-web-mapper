package org.svgroz;

import org.svgroz.slim.api.Controller;
import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Parameter;
import org.svgroz.slim.api.Post;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
@Controller("/Foo")
public interface Foo {
    @Get("/hello1")
    void doGetInFoo(@Parameter(value = "p1") String x);

    @Post("hello2")
    void doPostInFoo(@Parameter(value = "p2") String x, @Parameter("p3") BigDecimal y);

    @Get("hello3")
    @Post({"there", "world"})
    Map<String, String> doBothInFoo(@Parameter(value = "p1", required = false) String y, @Parameter("p2") BigDecimal z);
}
