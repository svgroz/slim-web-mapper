<#assign argTypeToMethod = { "REQUIRED_STRING": "getRequiredString", "OPTIONAL_STRING": "getOptionalString", "REQUIRED_NUMERIC": "getRequiredNumeric", "OPTIONAL_NUMERIC": "getOptionalNumeric" }>
package ${ctx.packageName};

<#list ctx.imports as import>
import ${import};
</#list>

public class ${ctx.className}ControllerServletIpml extends HttpServlet implements BasicServlet<${ctx.className}> {
    private ${ctx.className} target;
    private Function<Object, byte[]> serializer;

    private final Map<String, Function<HttpServletRequest, Optional<Object>>> getMappings = Map.ofEntries(
    <#list ctx.getMethods as method>
            Map.entry("${method.methodName}", this::doGet_${method.methodName})<#sep>,</#sep>
    </#list>
    );

    private final Map<String, Function<HttpServletRequest, Optional<Object>>> postMappings = Map.ofEntries(
    <#list ctx.postMethods as method>
            Map.entry("${method.methodName}", this::doPost_${method.methodName})<#sep>,</#sep>
    </#list>
    );

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final var reqPath = req.getRequestURI();
        final var mappedFunction = getMappings.get(reqPath);

        if (mappedFunction == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        mappedFunction.apply(req)
                .map(serializer)
                .ifPresentOrElse(
                        r -> {
                            resp.setStatus(HttpServletResponse.SC_OK);
                            try {
                                resp.getOutputStream().write(r);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        },
                        () -> {
                            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        }
                );
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final var reqPath = req.getRequestURI();
        final var mappedFunction = getMappings.get(reqPath);

        if (mappedFunction == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        mappedFunction.apply(req)
                .map(serializer)
                .ifPresentOrElse(
                        r -> {
                            resp.setStatus(HttpServletResponse.SC_OK);
                            try {
                                resp.getOutputStream().write(r);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        },
                        () -> {
                            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                        }
                );
    }

    @Override
    public void setTarget(${ctx.className} target) {
        this.target = target;
    }

    @Override
    public void setSerializer(Function<Object, byte[]> serializer) {
        this.serializer = serializer;
    }
    <#list ctx.getMethods as method>
    <#assign prefix = "doGet"/>

    <#include "InternalMethod.ftl"/>

    </#list>
    <#list ctx.postMethods as method>
    <#assign prefix = "doPost"/>

    <#include "InternalMethod.ftl"/>

    </#list>
}
