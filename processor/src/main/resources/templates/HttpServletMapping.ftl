package ${ctx.packageName};

<#list ctx.imports as import>
import ${import};
</#list>

public class ${ctx.className} extends HttpServlet {
    <#if ctx.getMethods?size != 0>
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        <#list ctx.getMethods as get>
        <#list get.argumentList as arg>
        Optional.of(req.getParameter("// TODO")).orElseThrow()<#sep>,
        </#list>
        </#list>

    }
    </#if>

    <#if ctx.postMethods?size != 0>
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

    }
    </#if>
}