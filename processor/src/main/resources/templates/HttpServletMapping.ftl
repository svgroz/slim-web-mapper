<#assign argTypeToMethod = { "REQUIRED_STRING": "getRequiredString", "OPTIONAL_STRING": "getOptionalString", "REQUIRED_NUMERIC": "getRequiredNumeric", "OPTIONAL_NUMERIC": "getOptionalNumeric" }>
package ${ctx.packageName};

<#list ctx.imports as import>
import ${import};
</#list>

@WebServlet(urlPatterns = {
    <#list ctx.rootUrls as rootUrl>
    "${rootUrl}"<#sep>,</#sep>
    </#list>
})
public class ${ctx.className}ControllerServletIpml extends HttpServlet implements BasicServlet<${ctx.className}> {
    private ${ctx.className} target;
    private Function<Object, byte[]> serializer;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final var reqPath = RequestResponseUtils.getNormalizedRequestUrl(req);
        switch (reqPath) {
            <#list ctx.methods as method>
            <#if method.getUrls?has_content>
            <#list method.getUrls as url>
            case "${url}":
            </#list>
                RequestResponseUtils.processFunction(req, resp, this::_${method.methodName}, serializer);
                return;
            </#if>
            </#list>
            default:
                RequestResponseUtils.handleNotFound(resp);
                return;
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final var reqPath = RequestResponseUtils.getNormalizedRequestUrl(req);
        switch (reqPath) {
            <#list ctx.methods as method>
            <#if method.postUrls?has_content>
            <#list method.postUrls as url>
            case "${url}":
            </#list>
                RequestResponseUtils.processFunction(req, resp, this::_${method.methodName}, serializer);
                return;
            </#if>
            </#list>
            default:
                RequestResponseUtils.handleNotFound(resp);
                return;
        }
    }

    @Override
    public void setTarget(${ctx.className} target) {
        this.target = target;
    }

    @Override
    public void setSerializer(Function<Object, byte[]> serializer) {
        this.serializer = serializer;
    }
    <#list ctx.methods as method>
    <#assign prefix = "_"/>

    <#include "InternalMethod.ftl"/>

    </#list>
}
