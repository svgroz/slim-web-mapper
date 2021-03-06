    private Optional<Object> ${prefix}${method.methodName}(final HttpServletRequest req) {
        <#list method.argumentList as arg>
        final var _${arg.argumentName} = RequestParameterMapper.${argTypeToMethod[arg.argumentType]}("${arg.argumentName}", req);
        </#list>
        <#if method.isVoid>
        target.${method.methodName}(<#list method.argumentList as arg>_${arg.argumentName}<#sep>, </#sep></#list>);
        return Optional.empty();
        <#else>
        final var result = target.${method.methodName}(<#list method.argumentList as arg>_${arg.argumentName}<#sep>, </#sep></#list>);
        return Optional.ofNullable(result);
        </#if>
    }