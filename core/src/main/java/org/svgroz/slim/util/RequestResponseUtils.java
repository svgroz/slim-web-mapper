package org.svgroz.slim.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class RequestResponseUtils {
    private static final Logger LOG = LoggerFactory.getLogger(RequestResponseUtils.class);

    private RequestResponseUtils() {

    }

    public static void processFunction(
            HttpServletRequest request,
            HttpServletResponse response,
            Function<HttpServletRequest, Optional<Object>> f,
            Function<Object, byte[]> serializer
    ) {
        Optional<Object> result = f.apply(request);
        if (result.isPresent()) {
            byte[] rawResult = serializer.apply(result.get());
            try {
                response.getOutputStream().write(rawResult);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (IOException ex) {
                LOG.warn("", ex);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public static void handleNotFound(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
