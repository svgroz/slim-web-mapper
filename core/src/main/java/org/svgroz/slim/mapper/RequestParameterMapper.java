package org.svgroz.slim.mapper;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RequestParameterMapper {
    private RequestParameterMapper() {

    }

    public static String getRequiredString(String name, HttpServletRequest req) {
        final var res = req.getParameter(name);
        if (res == null) {
            throw new NoSuchElementException("No value present for parameter: " + name);
        }
        return res;
    }

    public static String getOptionalString(String name, HttpServletRequest req) {
        return req.getParameter(name);
    }

    public static BigDecimal getRequiredNumeric(String name, HttpServletRequest req) {
        final var res = req.getParameter(name);
        if (res == null) {
            throw new NoSuchElementException("No value present for parameter: " + name);
        }
        return new BigDecimal(res);
    }

    public static BigDecimal getOptionalNumeric(String name, HttpServletRequest req) {
        final var res = req.getParameter(name);
        if (res == null) {
            return null;
        }
        return new BigDecimal(res);
    }
}
