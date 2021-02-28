<#assign argTypeToMethod = { "REQUIRED_STRING": "getRequiredString", "OPTIONAL_STRING": "getOptionalString", "REQUIRED_NUMERIC": "getRequiredNumeric", "OPTIONAL_NUMERIC": "getOptionalNumeric" }>

package ${ctx.packageName};

<#list ctx.imports as import>
import ${import};
</#list>

public class ${ctx.className} extends HttpServlet {
    <#list ctx.getMethods as get>
    private final SortedSet<String> _${get.methodName}GetMappings = new TreeSet<>(
        Set.of(
            <#list get.urls as url>
            "${url}"<#sep>,</#sep>
            </#list>
        )
    );
    </#list>

    <#list ctx.postMethods as get>
    private final SortedSet<String> _${get.methodName}PostMappings = new TreeSet<>(
        Set.of(
            <#list get.urls as url>
            "${url}"<#sep>,</#sep>
            </#list>
        )
    );
    </#list>

    <#if ctx.getMethods?size != 0>
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String reqPath = req.getRequestURI();
        <#list ctx.getMethods as get>
        {
        <#list get.argumentList as arg>
            var _${arg.argumentName} = ${argTypeToMethod[arg.argumentType]}("${arg.argumentName}", req);
        </#list>
        }
        </#list>
    }
    </#if>

    <#if ctx.postMethods?size != 0>
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

    }
    </#if>

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
