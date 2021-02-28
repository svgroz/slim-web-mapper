package org.svgroz;

import org.svgroz.slim.api.Controller;
import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Parameter;

import java.math.BigDecimal;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
@Controller
public interface Foo {


    @Get
    void doPostInFoo(@Parameter("p1") String y, @Parameter("p2") BigDecimal z);
}
