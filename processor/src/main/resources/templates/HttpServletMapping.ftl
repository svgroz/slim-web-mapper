<#assign argTypeToMethod = { "REQUIRED_STRING": "getRequiredString", "OPTIONAL_STRING": "getOptionalString", "REQUIRED_NUMERIC": "getRequiredNumeric", "OPTIONAL_NUMERIC": "getOptionalNumeric" }>
package ${ctx.packageName};

<#list ctx.imports as import>
import ${import};
</#list>

public class ${ctx.className}ControllerServletIpml extends HttpServlet implements BasicServlet<${ctx.className}> {
    private ${ctx.className} target;
    private Function<Object, byte[]> serializer;

    <#assign prefix = "doGet"/>
    <#assign methods = ctx.getMethods/>
    <#include "Method.ftl"/>


    <#assign prefix = "doPost"/>
    <#assign methods = ctx.postMethods/>
    <#include "Method.ftl"/>

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
