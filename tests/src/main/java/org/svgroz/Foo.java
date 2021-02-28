package org.svgroz;

import org.svgroz.slim.api.Controller;
import org.svgroz.slim.api.Get;
import org.svgroz.slim.api.Parameter;
import org.svgroz.slim.api.Post;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
@Controller
public interface Foo {
    @Get
    void doGetInFoo(@Parameter String x);

    @Post
    void doPostInFoo(@Parameter String z);
}
