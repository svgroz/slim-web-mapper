package org.svgroz;

import org.svgroz.slim.api.Controller;
import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Parameter;
import org.svgroz.slim.api.Post;

import java.math.BigDecimal;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
@Controller
public interface Foo {
    @Get("hello")
    @Post({"there", "world"})
    void doPostInFoo(@Parameter(value = "p1", required = false) String y, @Parameter("p2") BigDecimal z);
}
