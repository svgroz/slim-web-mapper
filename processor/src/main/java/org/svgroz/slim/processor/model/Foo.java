package org.svgroz.slim.processor.model;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class Foo extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String reqPath = req.getRequestURI();
        {
            var _p1 = getOptionalString("p1", req);
            var _p2 = getRequiredNumeric("p2", req);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

    }

    protected String getRequiredString(String name, HttpServletRequest req) {
        return Optional.ofNullable(req.getParameter(name)).orElseThrow();
    }

    protected String getOptionalString(String name, HttpServletRequest req) {
        return Optional.ofNullable(req.getParameter(name)).orElseThrow();
    }

    protected BigDecimal getRequiredNumeric(String name, HttpServletRequest req) {
        return Optional.ofNullable(req.getParameter(name)).map(BigDecimal::new).orElseThrow();
    }

    protected BigDecimal getOptionalNumeric(String name, HttpServletRequest req) {
        return Optional.ofNullable(req.getParameter(name)).map(BigDecimal::new).orElse(null);
    }
}

