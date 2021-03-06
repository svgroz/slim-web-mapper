package org.svgroz;

import com.google.gson.Gson;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Simon Grozovsky svgroz@outlook.com
 */
public class Application {
    public static void main(String[] args) throws Exception {
        var server = new Server();
        var serverConnector = new ServerConnector(server, 1, 1, new HttpConnectionFactory());
        serverConnector.setPort(8080);
        serverConnector.setHost("localhost");
        serverConnector.setAcceptQueueSize(128);

        var servletContext = new ServletContextHandler();
        FooControllerServletIpml servlet = new FooControllerServletIpml();

        servlet.setTarget(new FooImpl());
        servlet.setSerializer(new SerializerImpl());

        servletContext.addServlet(new ServletHolder(servlet), "/");

        server.addConnector(serverConnector);
        server.setHandler(servletContext);
        server.start();
    }

    public static class FooImpl implements Foo {
        @Override
        public void doGetInFoo(final String x) {

        }

        @Override
        public void doPostInFoo(final String x, final BigDecimal y) {

        }

        @Override
        public Map<String, String> doBothInFoo(final String y, final BigDecimal z) {
            return Map.of("hello", "world");
        }
    }

    public static class SerializerImpl implements Function<Object, byte[]> {
        private final Gson gson = new Gson();

        @Override
        public byte[] apply(final Object o) {
            return gson.toJson(o).getBytes(StandardCharsets.UTF_8);
        }
    }
}
