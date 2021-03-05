package org.svgroz.slim.servlet;

import jakarta.servlet.Servlet;

import java.util.function.Function;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface BasicServlet<T> extends Servlet {
    void setTarget(T t);

    void setSerializer(Function<Object, byte[]> serializer);
}
