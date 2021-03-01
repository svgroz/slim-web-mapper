package org.svgroz.slim.servlet;

import jakarta.servlet.Servlet;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public interface BasicServlet<T> extends Servlet {
    void setTarget(T t);
}
