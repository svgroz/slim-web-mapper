<#assign argTypeToMethod = { "REQUIRED_STRING": "getRequiredString", "OPTIONAL_STRING": "getOptionalString", "REQUIRED_NUMERIC": "getRequiredNumeric", "OPTIONAL_NUMERIC": "getOptionalNumeric" }>

package ${ctx.packageName};

<#list ctx.imports as import>
import ${import};
</#list>

public class ${ctx.className}ControllerServletIpml extends HttpServlet {
    private ${ctx.className} ${ctx.className?uncap_first};

    <#list ctx.getMethods as get>
    private final Set<String> _${get.methodName}GetMappings = Set.of(
            <#list get.urls as url>
            "${url}"<#sep>,</#sep>
            </#list>
    );
    </#list>

    <#list ctx.postMethods as get>
    private final Set<String> _${get.methodName}PostMappings = Set.of(
            <#list get.urls as url>
            "${url}"<#sep>,</#sep>
            </#list>
    );
    </#list>

    <#if ctx.getMethods?size != 0>
    <#assign methodName = "doGet"/>
    <#assign methods = ctx.getMethods />
    <#include "doMethod.ftl"/>
    </#if>

    <#if ctx.getMethods?size != 0>
    <#assign methodName = "doPost"/>
    <#assign methods = ctx.postMethods />
    <#include "doMethod.ftl"/>
    </#if>

    public void set${ctx.className}(${ctx.className} ${ctx.className?uncap_first}) {
        this.${ctx.className?uncap_first} = ${ctx.className?uncap_first};
    }
}
